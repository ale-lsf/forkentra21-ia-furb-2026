package com.delegrego.exemplo_spring_boot_2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {

	/**
	 * Redireciona para a página de login se o usuário não estiver autenticado
	 * Caso contrário, redireciona para a página de lista de funcionários
	 * @param autenticacao - Objeto de autenticação do Spring Security
	 * @return Redirecionamento para a página apropriada
	 */
	@GetMapping
	public String redirecionar(Authentication autenticacao) {
		if (autenticacao != null && autenticacao.isAuthenticated()) {
			return "redirect:/pages/html/lista-funcionarios.html";
		}
		return "redirect:/pages/html/login.html";
	}

}
