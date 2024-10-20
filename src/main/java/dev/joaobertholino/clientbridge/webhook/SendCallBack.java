package dev.joaobertholino.clientbridge.webhook;

import dev.joaobertholino.clientbridge.model.Transaction;
import dev.joaobertholino.clientbridge.response.TransactionResponse;
import org.springframework.http.ProblemDetail;

public interface SendCallBack {
	TransactionResponse sendTransactionCallback(Transaction transaction);

	void sendExceptionCallBack(ProblemDetail exceptionResponse);
}
