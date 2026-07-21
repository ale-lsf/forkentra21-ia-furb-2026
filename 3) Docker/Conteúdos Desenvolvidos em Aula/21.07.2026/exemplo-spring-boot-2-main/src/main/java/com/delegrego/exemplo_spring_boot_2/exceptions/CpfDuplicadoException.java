package com.delegrego.exemplo_spring_boot_2.exceptions;

/**
 * Exceção lançada quando um CPF duplicado é detectado.
 */
public class CpfDuplicadoException extends RuntimeException {

	public CpfDuplicadoException() {
		super();
	}

	public CpfDuplicadoException(String message) {
		super(message);
	}

	public CpfDuplicadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CpfDuplicadoException(Throwable cause) {
		super(cause);
	}
}
