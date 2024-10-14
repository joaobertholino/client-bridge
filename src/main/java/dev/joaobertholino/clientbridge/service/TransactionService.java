package dev.joaobertholino.clientbridge.service;

import dev.joaobertholino.clientbridge.request.TransactionRequest;
import dev.joaobertholino.clientbridge.response.TransactionResponse;
import jakarta.transaction.Transactional;

public interface TransactionService {
	@Transactional
	TransactionResponse makeTransaction(TransactionRequest transactionRequest);
}
