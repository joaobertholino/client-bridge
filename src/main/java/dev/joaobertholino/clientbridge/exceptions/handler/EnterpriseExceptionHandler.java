package dev.joaobertholino.clientbridge.exceptions.handler;

import dev.joaobertholino.clientbridge.exceptions.EnterpriseNotFoundException;
import dev.joaobertholino.clientbridge.exceptions.response.ExceptionResponse;
import dev.joaobertholino.clientbridge.webhook.SendCallBack;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class EnterpriseExceptionHandler {
	private final SendCallBack sendCallBack;

	@ExceptionHandler(EnterpriseNotFoundException.class)
	public ResponseEntity<ExceptionResponse> enterpriseNotFoundException(EnterpriseNotFoundException enterpriseNotFoundException, HttpServletRequest request) {
		LocalDateTime timestamp = LocalDateTime.now();
		HttpStatus status = HttpStatus.NOT_FOUND;
		ExceptionResponse exceptionResponse = new ExceptionResponse(timestamp.toString(), status, "Enterprise not found", enterpriseNotFoundException.getMessage(), request.getRequestURI());
		this.sendCallBack.sendExceptionCallBack(exceptionResponse);
		return ResponseEntity.status(status).body(exceptionResponse);
	}
}
