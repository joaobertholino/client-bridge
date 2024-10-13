package dev.joaobertholino.clientbridge.exceptions.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

public record ExceptionResponse(
		@NotBlank(message = "The timestemp field cannot be null or empty.")
		String timestamp,

		@NotNull(message = "The HTTP status field cannot be null.")
		HttpStatus status,

		@NotBlank(message = "The error field cannot be null or empty.")
		String error,

		@NotBlank(message = "The message field cannot be null or empty.")
		String message,

		@NotBlank(message = "The path field cannot be null or empty.")
		String path
) {
}
