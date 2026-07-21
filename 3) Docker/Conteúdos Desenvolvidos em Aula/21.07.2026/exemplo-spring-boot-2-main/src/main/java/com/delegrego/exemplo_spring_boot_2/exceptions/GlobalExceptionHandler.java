package com.delegrego.exemplo_spring_boot_2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe para tratamento global de exceções na aplicação.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Tratamento para RuntimeException.
	 * 
	 * @param ex - exceção lançada
	 * @return ResponseEntity com status 400 (Bad Request) e mensagem da exceção
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> tratarRuntimeException(RuntimeException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	/**
	 * Tratamento para EmailDuplicadoException.
	 * 
	 * @param ex - exceção lançada
	 * @return ResponseEntity com status 409 (Conflict) e mensagem da exceção
	 */
	@ExceptionHandler(EmailDuplicadoException.class)
	public ResponseEntity<String> tratarEmailDuplicadoException(EmailDuplicadoException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}

	/**
	 * Tratamento para CpfDuplicadoException.
	 * 
	 * @param ex - exceção lançada
	 * @return ResponseEntity com status 409 (Conflict) e mensagem da exceção
	 */
	@ExceptionHandler(CpfDuplicadoException.class)
	public ResponseEntity<String> tratarCpfDuplicadoException(CpfDuplicadoException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}

	/**
	 * Tratamento para DepartamentoNaoEncontradoException.
	 * 
	 * @param ex - exceção lançada
	 * @return ResponseEntity com status 409 (Conflict) e mensagem da exceção
	 */
	@ExceptionHandler(DepartamentoNaoEncontradoException.class)
	public ResponseEntity<String> tratarDepartamentoNaoEncontradoException(DepartamentoNaoEncontradoException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}

	/**
	 * Tratamento para FuncionarioNaoEncontradoException.
	 * 
	 * @param ex - exceção lançada
	 * @return ResponseEntity com status 409 (Conflict) e mensagem da exceção
	 */
	@ExceptionHandler(FuncionarioNaoEncontradoException.class)
	public ResponseEntity<String> tratarFuncionarioNaoEncontradoException(FuncionarioNaoEncontradoException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}

	/**
	 * Tratamento para ExclusaoDeDepartamentoNaoPermitidaException.
	 * 
	 * @param ex - exceção lançada
	 * @return ResponseEntity com status 409 (Conflict) e mensagem da exceção
	 */
	@ExceptionHandler(ExclusaoDeDepartamentoNaoPermitidaException.class)
	public ResponseEntity<String> tratarExclusaoDeDepartamentoNaoPermitidaException(
			ExclusaoDeDepartamentoNaoPermitidaException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}

	/**
	 * Tratamento para UsuarioAutenticadoNaoEncontradoException.
	 * 
	 * @param ex - exceção lançada
	 * @return ResponseEntity com status 404 (Not Found) e mensagem da exceção
	 */
	@ExceptionHandler(UsuarioAutenticadoNaoEncontradoException.class)
	public ResponseEntity<String> tratarUsuarioAutenticadoNaoEncontradoException(
			UsuarioAutenticadoNaoEncontradoException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	/**
	 * Tratamento para AuthorizationDeniedException.
	 * 
	 * @param ex - exceção lançada
	 * @return ResponseEntity com status 403 e mensagem "Acesso negado"
	 */
	@ExceptionHandler(AuthorizationDeniedException.class)
	public ResponseEntity<String> tratarAuthorizationDeniedException(AuthorizationDeniedException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
	}

}