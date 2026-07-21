package com.delegrego.exemplo_spring_boot_2.exceptions;

/**
 * Exceção lançada quando um departamento não é encontrado.
 */
public class DepartamentoNaoEncontradoException extends RuntimeException {

	public DepartamentoNaoEncontradoException() {
		super();
	}

	public DepartamentoNaoEncontradoException(String message) {
		super(message);
	}

	public DepartamentoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DepartamentoNaoEncontradoException(Throwable cause) {
		super(cause);
	}

}
