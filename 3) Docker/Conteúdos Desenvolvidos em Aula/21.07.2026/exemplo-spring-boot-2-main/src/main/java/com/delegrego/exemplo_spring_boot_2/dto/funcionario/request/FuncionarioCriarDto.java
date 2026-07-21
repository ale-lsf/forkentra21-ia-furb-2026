package com.delegrego.exemplo_spring_boot_2.dto.funcionario.request;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.delegrego.exemplo_spring_boot_2.dto.endereco.request.EnderecoRequestDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
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
public class FuncionarioCriarDto {

	@NotBlank
	@Size(max = 100)
	private String nome;

	@NotBlank
	// @CPF
	private String cpf;

	@NotBlank
	@Email
	@Size(max = 100)
	private String email;

	@NotBlank
	@Size(max = 100)
	private String senha;

	@NotNull
	@PastOrPresent
	private LocalDate dataNascimento;

	@Digits(integer = 10, fraction = 2)
	@PositiveOrZero
	private BigDecimal salario;

	private boolean gerente;

	@NotNull
	@Valid
	private EnderecoRequestDto endereco;

	@NotNull
	private BigInteger idDepartamento;

}
