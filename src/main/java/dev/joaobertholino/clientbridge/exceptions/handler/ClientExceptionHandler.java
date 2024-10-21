package dev.joaobertholino.clientbridge.exceptions.handler;

import dev.joaobertholino.clientbridge.exceptions.ClientNotFoundException;
import dev.joaobertholino.clientbridge.webhook.SendCallBack;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
@RequiredArgsConstructor
public class ClientExceptionHandler {
	private final SendCallBack sendCallBack;

	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<ProblemDetail> clientNotFoundException(ClientNotFoundException clientNotFoundException, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;

		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, clientNotFoundException.getMessage());
		problemDetail.setTitle("Client not found");

		this.sendCallBack.sendExceptionCallBack(problemDetail);
		return ResponseEntity.status(status).body(problemDetail);
	}
}
