package com.delegrego.exemplo_spring_boot_2.dto.departamento.response;

import java.math.BigInteger;

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
public class DepartamentoResponseDto {

	private BigInteger idDepartamento;

	private String nmDepartamento;

}
