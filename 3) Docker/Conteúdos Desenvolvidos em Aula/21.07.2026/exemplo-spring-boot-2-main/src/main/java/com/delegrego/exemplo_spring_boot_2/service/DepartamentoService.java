package com.delegrego.exemplo_spring_boot_2.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.delegrego.exemplo_spring_boot_2.dto.departamento.request.DepartamentoRequestDto;
import com.delegrego.exemplo_spring_boot_2.dto.departamento.response.DepartamentoResponseDto;
import com.delegrego.exemplo_spring_boot_2.entity.DepartamentoEntity;
import com.delegrego.exemplo_spring_boot_2.exceptions.DepartamentoNaoEncontradoException;
import com.delegrego.exemplo_spring_boot_2.exceptions.ExclusaoDeDepartamentoNaoPermitidaException;
import com.delegrego.exemplo_spring_boot_2.repo.DepartamentoRepository;
import com.delegrego.exemplo_spring_boot_2.repo.FuncionarioRepository;

@Service
//@RequiredArgsConstructor -> Pode ser utilizado em vez do construtor
public class DepartamentoService {

	// Maneira correta de acoplar camada de repositório
	private final DepartamentoRepository repo;

	private final FuncionarioRepository funcionarioRepo;

	// A anotação de @Autowired é opcional
	// O Spring já faz a injeção automaticamente se:
	// A classe é um bean (@Service, por exemplo)
	// E a classe tem somente 1 construtor
	@Autowired
	public DepartamentoService(DepartamentoRepository repo, FuncionarioRepository funcionarioRepo) {
		this.repo = repo;
		this.funcionarioRepo = funcionarioRepo;
	}

	@PreAuthorize("hasRole('GERENTE')")
	public DepartamentoEntity cadastrarDepartamento(DepartamentoRequestDto departamentoDTO) {

		DepartamentoEntity departamentoEntity = new DepartamentoEntity();

		departamentoEntity.setNmDepartamento(departamentoDTO.getNmDepartamento());

		return repo.save(departamentoEntity);
	}

	@PreAuthorize("hasAnyRole('FUNCIONARIO', 'GERENTE')")
	public List<DepartamentoResponseDto> listarDepartamentos() {
		List<DepartamentoEntity> listaDepartamentoEntity = repo.findAll();

		List<DepartamentoResponseDto> listaDepartamentoDto = new ArrayList<DepartamentoResponseDto>();

		for (DepartamentoEntity d : listaDepartamentoEntity) {
			DepartamentoResponseDto departamentoDto = new DepartamentoResponseDto();
			departamentoDto.setIdDepartamento(d.getIdDepartamento());
			departamentoDto.setNmDepartamento(d.getNmDepartamento());

			listaDepartamentoDto.add(departamentoDto);
		}

		return listaDepartamentoDto;
	}

	@PreAuthorize("hasRole('GERENTE')")
	public DepartamentoResponseDto obterDepartamentoPorId(BigInteger id) {

		DepartamentoEntity departamentoEntity = repo.findById(id)
				.orElseThrow(() -> new DepartamentoNaoEncontradoException("Departamento não encontrado"));

		DepartamentoResponseDto departamentoDto = new DepartamentoResponseDto();
		departamentoDto.setIdDepartamento(departamentoEntity.getIdDepartamento());
		departamentoDto.setNmDepartamento(departamentoEntity.getNmDepartamento());

		return departamentoDto;
	}

	@PreAuthorize("hasAnyRole('FUNCIONARIO', 'GERENTE')")
	public List<DepartamentoResponseDto> pesquisarDepartamentos(String pesquisa) {
		List<DepartamentoEntity> listaDepartamentoEntity = repo.findByNmDepartamentoContainingIgnoreCase(pesquisa);
		List<DepartamentoResponseDto> listaDepartamentoDto = new ArrayList<DepartamentoResponseDto>();

		for (DepartamentoEntity d : listaDepartamentoEntity) {
			DepartamentoResponseDto departamentoDto = new DepartamentoResponseDto();
			departamentoDto.setIdDepartamento(d.getIdDepartamento());
			departamentoDto.setNmDepartamento(d.getNmDepartamento());

			listaDepartamentoDto.add(departamentoDto);
		}

		return listaDepartamentoDto;

	}

	@PreAuthorize("hasRole('GERENTE')")
	public DepartamentoEntity atualizarDepartamento(BigInteger id, DepartamentoRequestDto departamentoDTO) {

		DepartamentoEntity departamentoEntity = repo.findById(id)
				.orElseThrow(() -> new DepartamentoNaoEncontradoException("Departamento não encontrado"));

		departamentoEntity.setNmDepartamento(departamentoDTO.getNmDepartamento());

		return repo.save(departamentoEntity);
	}

	@PreAuthorize("hasRole('GERENTE')")
	public void deletarDepartamento(BigInteger id) {

		repo.findById(id).orElseThrow(() -> new DepartamentoNaoEncontradoException("Departamento não encontrado"));

		if (funcionarioRepo.existsByDepartamentoIdDepartamento(id)) {
			throw new ExclusaoDeDepartamentoNaoPermitidaException("Não pode excluir departamentos com funcionários");
		}

		repo.deleteById(id);
	}

}
