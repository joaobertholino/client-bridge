package dev.joaobertholino.clientbridge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_enterprise")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Enterprise {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "enterprise_id")
	private UUID id;

	@NotBlank(message = "The company name cannot be empty.")
	@Column(name = "enterprise_name", nullable = false, length = 150)
	private String name;

	@CNPJ(message = "Invalid CNPJ.")
	@NotBlank(message = "The company's CNPJ cannot be empty.")
	@Column(name = "enterprise_cnpj", nullable = false, unique = true)
	private String cnpj;

	@PositiveOrZero(message = "The company's balance must be positive or equal to zero.")
	@NotNull(message = "The company's balance cannot be zero.")
	@Column(name = "enterprise_balance", nullable = false, precision = 10, scale = 2)
	private BigDecimal balance;
}
