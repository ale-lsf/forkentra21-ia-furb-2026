package com.delegrego.exemplo_spring_boot_2.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.delegrego.exemplo_spring_boot_2.entity.FuncionarioEntity;
import com.delegrego.exemplo_spring_boot_2.repo.FuncionarioRepository;

import lombok.RequiredArgsConstructor;

/**
 * Serviço para carregar os detalhes do funcionário durante o processo de
 * autenticação.
 */

@Service
@RequiredArgsConstructor
public class FuncionarioDetailsService implements UserDetailsService {

	private final FuncionarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		FuncionarioEntity funcionario = repo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		return new FuncionarioDetails(funcionario); // Retorna os detalhes do funcionário para autenticação
	}

}