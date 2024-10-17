package dev.joaobertholino.clientbridge.mapper;

import dev.joaobertholino.clientbridge.model.Client;
import dev.joaobertholino.clientbridge.model.Enterprise;
import dev.joaobertholino.clientbridge.model.Transaction;
import dev.joaobertholino.clientbridge.request.TransactionRequest;
import dev.joaobertholino.clientbridge.response.ClientResponse;
import dev.joaobertholino.clientbridge.response.EnterpriseResponse;
import dev.joaobertholino.clientbridge.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TransactionMapper {
	private final ClientMapper clientMapper;
	private final EnterpriseMapper enterpriseMapper;

	public Transaction buildTransaction(@Validated Enterprise enterprise, @Validated Client client, @Validated TransactionRequest transactionRequest, @Validated BigDecimal feePercentage) {
		return new Transaction(null,
				client,
				enterprise,
				transactionRequest.value(),
				feePercentage,
				transactionRequest.transactionType(),
				LocalDateTime.now());
	}

	public TransactionResponse buildTransactionResponse(@Validated Transaction transaction) {
		ClientResponse clientResponse = this.clientMapper.buildClientResponse(transaction.getClient());
		EnterpriseResponse enterpriseResponse = this.enterpriseMapper.buildEnterpriseResponse(transaction.getEnterprise());

		return new TransactionResponse(transaction.getId(),
				clientResponse,
				enterpriseResponse,
				transaction.getValue(),
				transaction.getFeePercentage(),
				transaction.getTransactionType(),
				transaction.getDateTime().toString());
	}
}
