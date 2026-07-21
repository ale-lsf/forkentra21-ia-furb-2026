package com.delegrego.exemplo_spring_boot_2.repo;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delegrego.exemplo_spring_boot_2.entity.FuncionarioEntity;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, BigInteger> {

	// Derived queries

	/**
	 * Retorna uma lista de funcionários cujo nome, email ou cpf contenha o valor
	 * fornecido
	 * 
	 * @param nome  - O nome do funcionário
	 * @param email - O email do funcionário
	 * @param cpf   - O cpf do funcionário
	 * @return A lista de funcionários encontrados
	 */
	List<FuncionarioEntity> findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCaseOrCpfContaining(String nome,
			String email, String cpf);

	/**
	 * Retorna um funcionário com o email fornecido
	 * 
	 * @param email - O email do funcionário
	 * @return O funcionário, ou vazio se não encontrado
	 */
	Optional<FuncionarioEntity> findByEmail(String email);

	/**
	 * Retorna um boolean indicando se o email já existe
	 * 
	 * @param email - O email do funcionário
	 * @return Um boolean indicando se o email já existe
	 *
	 */
	boolean existsByEmail(String email);

	/**
	 * Retorna um boolean indicando se o cpf já existe
	 * 
	 * @param cpf - O cpf do funcionário
	 * @return Um boolean indicando se o cpf já existe
	 *
	 */
	boolean existsByCpf(String cpf);

	/**
	 * Retorna um boolean indicando se o cpf já existe para outro funcionário
	 * 
	 * @param cpf - O cpf do funcionário
	 * @param id  - A id do funcionário
	 * @return Um boolean indicando se o cpf já existe para outro funcionário
	 *
	 */
	boolean existsByCpfAndIdFuncionarioNot(String cpf, BigInteger id);

	/**
	 * Retorna um boolean indicando se o email já existe para outro funcionário
	 * diferente do fornecido.
	 * 
	 * @param email - O email do funcionário
	 * @param id    - A id do funcionário
	 * @return Um boolean indicando se o email já existe para outro funcionário
	 *         diferente do fornecido
	 *
	 */
	boolean existsByEmailAndIdFuncionarioNot(String email, BigInteger id);

	/**
	 * Retorna um boolean indicando se existe algum funcionário associado ao
	 * departamento fornecido.
	 * 
	 * @param id - A id do departamento
	 * @return Um boolean indicando se existe algum funcionário associado ao
	 *         departamento fornecido
	 */
	boolean existsByDepartamentoIdDepartamento(BigInteger id);
}
