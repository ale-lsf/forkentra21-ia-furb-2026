package com.delegrego.exemplo_spring_boot_2.exceptions;

/**
 * Exceção lançada quando um email duplicado é detectado
 */
public class EmailDuplicadoException extends RuntimeException {

	public EmailDuplicadoException() {
		super();
	}

	public EmailDuplicadoException(String message) {
		super(message);
	}

	public EmailDuplicadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailDuplicadoException(Throwable cause) {
		super(cause);
	}
}