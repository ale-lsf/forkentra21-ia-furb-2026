package com.delegrego.exemplo_spring_boot_2.dto.endereco.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class EnderecoRequestDto {

	@NotBlank
	@Size(min = 2, max = 2)
	private String estado;

	@NotBlank
	@Size(max = 100)
	private String cidade;

	@NotBlank
	@Size(max = 100)
	private String bairro;

	@NotBlank
	@Size(max = 100)
	private String logradouro;

	@NotNull
	@Size(max = 10)
	@Pattern(regexp = "\\d+")
	private String numero;

	@NotBlank
	@Pattern(regexp = "\\d{8}")
	private String cep;

}
