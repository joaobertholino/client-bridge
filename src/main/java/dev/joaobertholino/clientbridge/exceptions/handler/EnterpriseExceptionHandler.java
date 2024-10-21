package dev.joaobertholino.clientbridge.exceptions.handler;

import dev.joaobertholino.clientbridge.exceptions.EnterpriseNotFoundException;
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
public class EnterpriseExceptionHandler {
	private final SendCallBack sendCallBack;

	@ExceptionHandler(EnterpriseNotFoundException.class)
	public ResponseEntity<ProblemDetail> enterpriseNotFoundException(EnterpriseNotFoundException enterpriseNotFoundException, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;

		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, enterpriseNotFoundException.getMessage());
		problemDetail.setTitle("Enterprise not found");

		this.sendCallBack.sendExceptionCallBack(problemDetail);
		return ResponseEntity.status(status).body(problemDetail);
	}
}
