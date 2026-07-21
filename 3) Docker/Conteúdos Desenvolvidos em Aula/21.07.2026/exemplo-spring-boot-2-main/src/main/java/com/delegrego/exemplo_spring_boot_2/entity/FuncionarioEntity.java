package com.delegrego.exemplo_spring_boot_2.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "funcionario")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FuncionarioEntity {

	@Id

	// Define que o valor do campo será gerado automaticamente pelo banco
	// (AUTO_INCREMENT)
	// IDENTITY é o tipo utilizado pelo MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger idFuncionario;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	// Restringe o atributo para não ser nulo, ser único
	// E ter limite de 11 caracteres
	@Column(name = "cpf", length = 11, nullable = false, unique = true)
	private String cpf;

	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "senha", length = 100, nullable = false)
	private String senha;

	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataNascimento;

	@Column(name = "salario", precision = 10, scale = 2)
	private BigDecimal salario;

	@Column(name = "gerente", nullable = false)
	private boolean gerente;

	// Indica que o campo abaixo é um objeto incorporado
	@Embedded
	private EnderecoEntity endereco;

	// Indica relacionamento muitos-para-um com Departamento
	@ManyToOne
	// Mapeia a chave estrangeira para departamento
	// name é o nome da coluna na tabela funcionario
	// referencedColumnName é o nome da coluna na tabela departamento
	@JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento", nullable = false)
	private DepartamentoEntity departamento;

	@ManyToOne
	@JoinColumn(name = "criado_por")
	private FuncionarioEntity criadoPor;

}
