package com.delegrego.exemplo_spring_boot_2.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.delegrego.exemplo_spring_boot_2.entity.FuncionarioEntity;

import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

/**
 * Implementação de UserDetails para representar os detalhes do funcionário
 * autenticado no sistema.
 * 
 * Esta classe encapsula um objeto Funcionario e fornece as informações
 * necessárias para o Spring Security durante o processo de autenticação e
 * autorização.
 * 
 * @see UserDetails
 */

@RequiredArgsConstructor
public class FuncionarioDetails implements UserDetails {

	private final FuncionarioEntity funcionario;

	/**
	 * Obtém o nome do funcionario validado
	 * 
	 * @return nome do funcionário
	 */
	public String obterNome() {
		return funcionario.getNome();
	}

	/**
	 * Verifica se o funcionário validado é um gerente
	 * 
	 * @return true se for gerente, false caso contrário
	 */
	public boolean isGerente() {
		return funcionario.isGerente();
	}

	public BigInteger obterId() {
		return funcionario.getIdFuncionario();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (funcionario.isGerente()) {
			return List.of(new SimpleGrantedAuthority("ROLE_GERENTE"));
		}
		return List.of(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return funcionario.getSenha();
	}

	@Override
	public String getUsername() {
		return funcionario.getEmail();
	}
}
