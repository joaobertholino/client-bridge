package dev.joaobertholino.clientbridge.service;

import dev.joaobertholino.clientbridge.enums.TransactionType;
import dev.joaobertholino.clientbridge.request.TransactionRequest;
import dev.joaobertholino.clientbridge.response.TransactionResponse;
import jakarta.transaction.Transactional;

import java.util.List;

public interface TransactionService {
	List<TransactionResponse> findTransactionByType(TransactionType type);

	@Transactional
	TransactionResponse makeTransaction(TransactionRequest transactionRequest);
}
