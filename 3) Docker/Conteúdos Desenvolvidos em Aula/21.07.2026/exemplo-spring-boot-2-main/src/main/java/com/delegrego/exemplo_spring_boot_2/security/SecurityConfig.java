package com.delegrego.exemplo_spring_boot_2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/pages/css/**", "/components/**", "/pages/js/**")
						.permitAll().anyRequest().authenticated()) // Exige autenticação para outras páginas
				.formLogin(form -> form.loginPage("/pages/html/login.html") // Define a página de login personalizada
						.loginProcessingUrl("/perform_login") // Action do formulário de login
						.usernameParameter("email") // Campo de email no formulário
						.passwordParameter("password") // Campo de senha no formulário
						.defaultSuccessUrl("/pages/html/lista-funcionarios.html", true).permitAll()) // Redirecionamento após login

				.logout(logout -> logout.permitAll()); // Permite logout sem autenticação
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}