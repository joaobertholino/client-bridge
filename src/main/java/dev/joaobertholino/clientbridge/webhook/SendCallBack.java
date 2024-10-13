package dev.joaobertholino.clientbridge.webhook;

import dev.joaobertholino.clientbridge.exceptions.response.ExceptionResponse;
import dev.joaobertholino.clientbridge.model.Transaction;
import dev.joaobertholino.clientbridge.response.TransactionResponse;

public interface SendCallBack {
	TransactionResponse sendTransactionCallback(Transaction transaction);

	void sendExceptionCallBack(ExceptionResponse exceptionResponse);
}
