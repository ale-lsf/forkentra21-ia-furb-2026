package com.delegrego.exemplo_spring_boot_2.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delegrego.exemplo_spring_boot_2.security.FuncionarioDetails;

/**
 * Controller para gerenciar informações do funcionário autenticado
 */

@RequestMapping("/funcionarios/me")
@CrossOrigin
@RestController
public class FuncionarioAutenticadoController {

	/**
	 * Endpoint para obter o nome do usuário autenticado
	 * 
	 * @param autenticacao - Informações da autenticação atual
	 * @return ResponseEntity com o nome do usuário autenticado
	 */
	@GetMapping("/nome")
	public ResponseEntity<?> obterNome(Authentication autenticacao) {
		FuncionarioDetails funcionarioDetails = (FuncionarioDetails) autenticacao.getPrincipal();
		return ResponseEntity.ok(Map.of("nome", funcionarioDetails.obterNome()));
	}

	@GetMapping("/gerente")
	public ResponseEntity<?> isGerente(Authentication autenticacao) {
		FuncionarioDetails funcionarioDetails = (FuncionarioDetails) autenticacao.getPrincipal();
		return ResponseEntity.ok(Map.of("gerente", funcionarioDetails.isGerente()));
	}

	@GetMapping("/id")
	public ResponseEntity<?> obterId(Authentication autenticacao) {
		FuncionarioDetails funcionarioDetails = (FuncionarioDetails) autenticacao.getPrincipal();
		return ResponseEntity.ok(Map.of("id", funcionarioDetails.obterId()));
	}

}
