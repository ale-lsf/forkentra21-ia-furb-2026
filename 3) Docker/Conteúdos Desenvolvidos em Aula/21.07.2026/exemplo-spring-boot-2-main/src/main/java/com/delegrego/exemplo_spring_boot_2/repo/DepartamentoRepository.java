package com.delegrego.exemplo_spring_boot_2.repo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delegrego.exemplo_spring_boot_2.entity.DepartamentoEntity;

public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, BigInteger> {

	// Derived queries

	/**
	 * Busca departamentos pelo nome
	 * 
	 * @param nome - nome do departamento
	 * @return Lista de departamentos encontrados
	 */
	List<DepartamentoEntity> findByNmDepartamentoContainingIgnoreCase(String nome);

}
