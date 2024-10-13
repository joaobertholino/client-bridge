package dev.joaobertholino.clientbridge.request;

import dev.joaobertholino.clientbridge.enums.TransactionType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionRequest(
		@NotNull(message = "The CPF cannot be null.")
		String clientCpf,

		@NotNull(message = "The CNPJ cannot be null.")
		String enterpriseCnpj,

		@NotNull(message = "The amount cannot be zero.")
		BigDecimal value,

		@NotNull(message = "The amount cannot be zero.")
		TransactionType transactionType
) {
}
