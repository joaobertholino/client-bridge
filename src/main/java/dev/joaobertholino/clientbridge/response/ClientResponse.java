package dev.joaobertholino.clientbridge.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ClientResponse(
	@NotNull(message = "The client id cannot be null.")
	UUID id,

	@NotBlank(message = "The customer's first name cannot be null or void.")
	String firstName,

	@NotBlank(message = "The customer's last name cannot be null or void.")
	String lastName,

	@NotBlank(message = "The customer's email cannot be null or empty.")
	String email,

	@NotBlank(message = "The customer's CPF cannot be null or empty.")
	String cpf,

	@NotNull(message = "The customer's balance cannot be zero.")
	BigDecimal balance
) {
}
