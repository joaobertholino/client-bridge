package dev.joaobertholino.clientbridge.model;

import dev.joaobertholino.clientbridge.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne
	@JoinColumn
	@NotNull(message = "The field referring to the customer cannot be null.")
	private Client client;

	@ManyToOne
	@JoinColumn
	@NotNull(message = "The field referring to the company cannot be null.")
	private Enterprise enterprise;

	@PositiveOrZero(message = "The transaction amount must be positive or equal to zero.")
	@NotNull(message = "The transaction amount cannot be null.")
	@Column(name = "transaction_value", nullable = false, precision = 10, scale = 2)
	private BigDecimal value;

	@DecimalMax(message = "The maximum fee allowed is 2 percent.", value = "0.02")
	@DecimalMin(message = "The minimum fee allowed is 1/2 percent.", value = "0.005")
	@NotNull(message = "The field referring to the tax rate percentage cannot be null.")
	@Column(name = "transaction_fee_percentage", nullable = false, precision = 3, scale = 2)
	private BigDecimal feePercentage;

	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "The field referring to the transaction type cannot be null.")
	@Column(name = "transaction_type", nullable = false)
	private TransactionType transactionType;

	@DateTimeFormat(pattern = "yyyy-MM-dd / HH:mm:ss")
	@NotNull(message = "The field relating to the date and time of the transaction cannot be null.")
	@Column(name = "transaction_data_time", nullable = false)
	private LocalDateTime dateTime;
}
