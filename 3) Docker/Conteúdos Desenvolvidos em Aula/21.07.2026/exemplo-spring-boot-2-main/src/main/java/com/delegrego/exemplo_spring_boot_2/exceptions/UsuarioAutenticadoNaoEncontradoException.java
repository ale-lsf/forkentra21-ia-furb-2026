package com.delegrego.exemplo_spring_boot_2.exceptions;

/**
 * Exceção lançada quando um usuário autenticado não é encontrado.
 */
public class UsuarioAutenticadoNaoEncontradoException extends RuntimeException {
	public UsuarioAutenticadoNaoEncontradoException() {
		super();
	}

	public UsuarioAutenticadoNaoEncontradoException(String message) {
		super(message);
	}

	public UsuarioAutenticadoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioAutenticadoNaoEncontradoException(Throwable cause) {
		super(cause);
	}
}
