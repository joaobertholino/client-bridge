package dev.joaobertholino.clientbridge.exceptions.handler;

import dev.joaobertholino.clientbridge.exceptions.TransactionInvalidException;
import dev.joaobertholino.clientbridge.exceptions.TransactionNotFoundException;
import dev.joaobertholino.clientbridge.webhook.SendCallBack;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class TransactionExceptionHandler {
	private final SendCallBack sendCallBack;

	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<ProblemDetail> transactionNotFound(TransactionNotFoundException transactionNotFoundException, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;

		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, transactionNotFoundException.getMessage());
		problemDetail.setTitle("Transaction not found.");

		this.sendCallBack.sendExceptionCallBack(problemDetail);
		return ResponseEntity.status(status).body(problemDetail);
	}

	@ExceptionHandler(TransactionInvalidException.class)
	public ResponseEntity<ProblemDetail> transactionInvalidException(TransactionInvalidException transactionInvalidException, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, transactionInvalidException.getMessage());
		problemDetail.setTitle("Transaction invalid.");

		this.sendCallBack.sendExceptionCallBack(problemDetail);
		return ResponseEntity.status(status).body(problemDetail);
	}
}
