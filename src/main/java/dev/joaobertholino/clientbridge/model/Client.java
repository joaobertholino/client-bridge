package dev.joaobertholino.clientbridge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "client_id")
	private UUID id;

	@NotBlank(message = "Customer first name cannot be empty.")
	@Column(name = "first_name", nullable = false, length = 20)
	private String firstName;

	@NotBlank(message = "Customer first name cannot be empty.")
	@Column(name = "last_name", nullable = false, length = 20)
	private String lastName;

	@Email(message = "Invalid email")
	@NotBlank(message = "The client's email cannot be empty.")
	@Column(name = "client_email", nullable = false, length = 50)
	private String email;

	@CPF(message = "Invalid CPF")
	@NotBlank(message = "The client's CPF cannot be empty.")
	@Column(name = "client_cpf", nullable = false, unique = true)
	private String cpf;

	@PositiveOrZero(message = "The customer's balance must be positive or equal to zero")
	@NotNull(message = "The customer's balance cannot be zero")
	@Column(name = "client_balance", nullable = false, precision = 10, scale = 2)
	private BigDecimal balance;
}
