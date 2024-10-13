package dev.joaobertholino.clientbridge.exceptions.handler;

import dev.joaobertholino.clientbridge.exceptions.TransactionInvalidException;
import dev.joaobertholino.clientbridge.exceptions.response.ExceptionResponse;
import dev.joaobertholino.clientbridge.webhook.SendCallBack;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@RequiredArgsConstructor
public class TransactionInvalidExceptionHandler {
	private final SendCallBack sendCallBack;

	@ExceptionHandler(TransactionInvalidException.class)
	public ResponseEntity<ExceptionResponse> transactionInvalidException(TransactionInvalidException transactionInvalidException, HttpServletRequest request) {
		LocalDateTime timestamp = LocalDateTime.now();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ExceptionResponse exceptionResponse = new ExceptionResponse(timestamp.toString(), status, "Invalid transaction", transactionInvalidException.getMessage(), request.getRequestURI());
		this.sendCallBack.sendExceptionCallBack(exceptionResponse);
		return ResponseEntity.status(status).body(exceptionResponse);
	}
}
