package com.delegrego.exemplo_spring_boot_2.dto.endereco.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnderecoResponseDto {

	private String estado;

	private String cidade;

	private String bairro;

	private String logradouro;

	private String numero;

	private String cep;

}
