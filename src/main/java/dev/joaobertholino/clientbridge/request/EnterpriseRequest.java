package dev.joaobertholino.clientbridge.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record EnterpriseRequest(
		@NotBlank(message = "The company name cannot be null or void.")
		String name,

		@NotBlank(message = "The company's CNPJ cannot be null or void.")
		String cnpj,

		@NotNull(message = "The company's balance cannot be zero.")
		BigDecimal balance
) {
}
