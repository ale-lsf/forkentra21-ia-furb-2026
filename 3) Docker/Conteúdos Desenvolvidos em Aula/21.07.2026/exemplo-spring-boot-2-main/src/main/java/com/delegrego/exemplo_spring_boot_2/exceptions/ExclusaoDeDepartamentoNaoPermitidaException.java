package com.delegrego.exemplo_spring_boot_2.exceptions;

/**
 * Exceção lançada quando a exclusão de um departamento não é permitida.
 */
public class ExclusaoDeDepartamentoNaoPermitidaException extends RuntimeException {
	public ExclusaoDeDepartamentoNaoPermitidaException() {
		super();
	}

	public ExclusaoDeDepartamentoNaoPermitidaException(String message) {
		super(message);
	}

	public ExclusaoDeDepartamentoNaoPermitidaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExclusaoDeDepartamentoNaoPermitidaException(Throwable cause) {
		super(cause);
	}
}
