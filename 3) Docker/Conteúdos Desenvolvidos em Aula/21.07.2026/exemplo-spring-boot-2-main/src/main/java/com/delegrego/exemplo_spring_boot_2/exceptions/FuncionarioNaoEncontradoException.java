package com.delegrego.exemplo_spring_boot_2.exceptions;

/**
 * Exceção lançada quando um funcionário não é encontrado.
 */
public class FuncionarioNaoEncontradoException extends RuntimeException {

	public FuncionarioNaoEncontradoException() {
		super();
	}

	public FuncionarioNaoEncontradoException(String message) {
		super(message);
	}

	public FuncionarioNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public FuncionarioNaoEncontradoException(Throwable cause) {
		super(cause);
	}
}