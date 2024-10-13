package dev.joaobertholino.clientbridge.service;

import dev.joaobertholino.clientbridge.request.TransactionRequest;
import dev.joaobertholino.clientbridge.response.TransactionResponse;

public interface TransactionService {
	TransactionResponse makeTransaction(TransactionRequest transactionRequest);
}
