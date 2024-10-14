package dev.joaobertholino.clientbridge.mapper;

import dev.joaobertholino.clientbridge.model.Client;
import dev.joaobertholino.clientbridge.model.Enterprise;
import dev.joaobertholino.clientbridge.model.Transaction;
import dev.joaobertholino.clientbridge.request.TransactionRequest;
import dev.joaobertholino.clientbridge.response.ClientResponse;
import dev.joaobertholino.clientbridge.response.EnterpriseResponse;
import dev.joaobertholino.clientbridge.response.TransactionResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class TransactionMapper {
	public static Transaction buildTransaction(@Validated Enterprise enterprise, @Validated Client client, @Validated TransactionRequest transactionRequest, @Validated BigDecimal feePercentage) {
		return new Transaction(null,
				client,
				enterprise,
				transactionRequest.value(),
				feePercentage,
				transactionRequest.transactionType(),
				LocalDateTime.now());
	}

	public static TransactionResponse buildTransactionResponse(@Validated Transaction transaction) {
		ClientResponse clientResponse = ClientMapper.buildClientResponse(transaction.getClient());
		EnterpriseResponse enterpriseResponse = EnterpriseMapper.buildEnterpriseResponse(transaction.getEnterprise());

		return new TransactionResponse(transaction.getId(),
				clientResponse,
				enterpriseResponse,
				transaction.getValue(),
				transaction.getFeePercentage(),
				transaction.getTransactionType(),
				transaction.getDateTime().toString());
	}
}
