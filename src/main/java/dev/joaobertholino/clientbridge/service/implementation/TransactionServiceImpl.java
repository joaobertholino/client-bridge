package dev.joaobertholino.clientbridge.service.implementation;

import dev.joaobertholino.clientbridge.enums.TransactionType;
import dev.joaobertholino.clientbridge.exceptions.ClientNotFoundException;
import dev.joaobertholino.clientbridge.exceptions.EnterpriseNotFoundException;
import dev.joaobertholino.clientbridge.exceptions.TransactionInvalidException;
import dev.joaobertholino.clientbridge.exceptions.TransactionNotFoundException;
import dev.joaobertholino.clientbridge.mapper.TransactionMapper;
import dev.joaobertholino.clientbridge.model.Client;
import dev.joaobertholino.clientbridge.model.Enterprise;
import dev.joaobertholino.clientbridge.model.Transaction;
import dev.joaobertholino.clientbridge.notification.NotificationComponent;
import dev.joaobertholino.clientbridge.repository.ClientRepository;
import dev.joaobertholino.clientbridge.repository.EnterpriseRepository;
import dev.joaobertholino.clientbridge.repository.TransactionRepository;
import dev.joaobertholino.clientbridge.request.TransactionRequest;
import dev.joaobertholino.clientbridge.response.TransactionResponse;
import dev.joaobertholino.clientbridge.service.TransactionService;
import dev.joaobertholino.clientbridge.webhook.SendCallBack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
	private final ClientRepository clientRepository;
	private final EnterpriseRepository enterpriseRepository;
	private final TransactionRepository transactionRepository;
	private final SendCallBack sendCallback;
	private final NotificationComponent notificationComponent;
	private final TransactionMapper transactionMapper;

	@Override
	public List<TransactionResponse> findTransactionByType(TransactionType type) {
		List<Transaction> transactionList = this.transactionRepository.findTransactionByTransactionType(type);

		if (!transactionList.isEmpty()) {
			return transactionList.stream().map(this.transactionMapper::buildTransactionResponse).toList();
		}
		throw new TransactionNotFoundException("No transactions of the type entered were found.");
	}

	@Override
	public TransactionResponse makeTransaction(TransactionRequest transactionRequest) {
		Client client = this.clientRepository.findClientByCpf(transactionRequest.clientCpf()).orElseThrow(() -> new ClientNotFoundException("Customer was not found."));
		Enterprise enterprise = this.enterpriseRepository.findEnterpriseByCnpj(transactionRequest.enterpriseCnpj()).orElseThrow(() -> new EnterpriseNotFoundException("Enterprise was not found."));

		BigDecimal feePercent = this.calculateFinalValue(transactionRequest.transactionType());
		this.updateEntities(enterprise, client, feePercent, transactionRequest.value(), transactionRequest.transactionType());

		Client clientSaved = this.clientRepository.save(client);
		Enterprise enterpriseSaved = this.enterpriseRepository.save(enterprise);

		Transaction transactionSaved = this.transactionRepository.save(this.transactionMapper.buildTransaction(enterpriseSaved, clientSaved, transactionRequest, feePercent));
		this.notificationComponent.sendNotification(clientSaved, transactionRequest.transactionType(), "successfully accomplished.");
		return this.sendCallback.sendTransactionCallback(transactionSaved);
	}

	private BigDecimal calculateFinalValue(TransactionType transactionType) {
		if (transactionType.equals(TransactionType.DEPOSIT)) {
			return BigDecimal.valueOf(0.01);
		}
		return BigDecimal.valueOf(0.02);
	}

	private void updateEntities(Enterprise enterprise, Client client, BigDecimal feePercent, BigDecimal value, TransactionType transactionType) {
		BigDecimal addedValue = value.add(value.multiply(feePercent));
		BigDecimal subtractValue = value.subtract(value.multiply(feePercent));

		if (transactionType.equals(TransactionType.DEPOSIT) && client.getBalance().doubleValue() >= addedValue.doubleValue()) {
			BigDecimal clientNewBalance = client.getBalance().subtract(value);
			client.setBalance(clientNewBalance);

			BigDecimal enterpriseNewBalance = enterprise.getBalance().add(subtractValue);
			enterprise.setBalance(enterpriseNewBalance);
		} else if (transactionType.equals(TransactionType.WITHDRAWAL) && enterprise.getBalance().doubleValue() >= addedValue.doubleValue()) {
			BigDecimal clientNewBalance = client.getBalance().add(value);
			client.setBalance(clientNewBalance);

			BigDecimal enterpriseNewBalance = enterprise.getBalance().subtract(addedValue);
			enterprise.setBalance(enterpriseNewBalance);
		} else {
			throw new TransactionInvalidException("Invalid transaction values.");
		}
	}
}
