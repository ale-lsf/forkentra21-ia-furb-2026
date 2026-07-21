package com.delegrego.exemplo_spring_boot_2.entity;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "departamento")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartamentoEntity {

	@Id

	// Define que o valor do campo será gerado automaticamente pelo banco
	// (AUTO_INCREMENT)
	// IDENTITY é o tipo utilizado pelo MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_departamento")
	private BigInteger idDepartamento;

	// Restringe o atributo para não ser nulo e ter limite de 50 caracteres
	@Column(name = "nm_departamento", nullable = false, length = 50)
	private String nmDepartamento;

}
