package com.delegrego.exemplo_spring_boot_2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Indica que esta classe pode ser incorporada em outra entidade JPA
@Embeddable

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnderecoEntity {

	@Column(name = "estado", nullable = false, length = 2)
	private String estado;

	@Column(name = "cidade", nullable = false, length = 100)
	private String cidade;

	@Column(name = "bairro", nullable = false, length = 100)
	private String bairro;

	@Column(name = "logradouro", nullable = false, length = 100)
	private String logradouro;

	@Column(name = "numero", length = 10)
	private String numero;

	@Column(name = "cep", length = 9)
	private String cep;

}
