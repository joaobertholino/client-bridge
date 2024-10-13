package dev.joaobertholino.clientbridge.response;

import dev.joaobertholino.clientbridge.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionResponse(
		@NotNull(message = "The transaction id cannot be null.")
		UUID id,

		@NotNull(message = "The client cannot be null.")
		ClientResponse client,

		@NotNull(message = "The company cannot be null.")
		EnterpriseResponse enterprise,

		@NotNull(message = "The transaction amount cannot be zero.")
		BigDecimal value,

		@NotNull(message = "The transaction fee amount cannot be zero.")
		BigDecimal feePercentage,

		@NotNull(message = "The transaction type cannot be null.")
		TransactionType transactionType,

		@DateTimeFormat(pattern = "yyyy-MM-dd / HH:mm:ss")
		@NotNull(message = "The date and time cannot be null.")
		String dateTime
) {
}
