package dev.joaobertholino.clientbridge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionType {
	DEPOSIT("Deposit"),
	WITHDRAWAL("Withdrawal");

	private final String transactionType;
}
