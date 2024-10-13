package dev.joaobertholino.clientbridge.exceptions;

public class TransactionInvalidException extends RuntimeException {
	public TransactionInvalidException(String message) {
		super(message);
	}
}
