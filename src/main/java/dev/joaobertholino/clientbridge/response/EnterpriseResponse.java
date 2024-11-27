package dev.joaobertholino.clientbridge.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record EnterpriseResponse(
	@NotNull(message = "The company id cannot be null.")
	UUID id,

	@NotBlank(message = "The company name cannot be empty.")
	String name,

	@NotBlank(message = "The company's CNPJ cannot be empty.")
	String cnpj,

	@NotNull(message = "The company's balance cannot be zero.")
	BigDecimal balance
) {
}
