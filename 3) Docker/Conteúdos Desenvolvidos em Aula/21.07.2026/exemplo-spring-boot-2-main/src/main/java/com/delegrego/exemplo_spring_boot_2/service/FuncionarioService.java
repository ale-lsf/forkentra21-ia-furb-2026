package com.delegrego.exemplo_spring_boot_2.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.delegrego.exemplo_spring_boot_2.dto.departamento.response.DepartamentoResponseDto;
import com.delegrego.exemplo_spring_boot_2.dto.endereco.response.EnderecoResponseDto;
import com.delegrego.exemplo_spring_boot_2.dto.funcionario.request.FuncionarioAtualizarDto;
import com.delegrego.exemplo_spring_boot_2.dto.funcionario.request.FuncionarioCriarDto;
import com.delegrego.exemplo_spring_boot_2.dto.funcionario.response.FuncionarioResponseDto;
import com.delegrego.exemplo_spring_boot_2.entity.DepartamentoEntity;
import com.delegrego.exemplo_spring_boot_2.entity.EnderecoEntity;
import com.delegrego.exemplo_spring_boot_2.entity.FuncionarioEntity;
import com.delegrego.exemplo_spring_boot_2.exceptions.CpfDuplicadoException;
import com.delegrego.exemplo_spring_boot_2.exceptions.DepartamentoNaoEncontradoException;
import com.delegrego.exemplo_spring_boot_2.exceptions.EmailDuplicadoException;
import com.delegrego.exemplo_spring_boot_2.exceptions.FuncionarioNaoEncontradoException;
import com.delegrego.exemplo_spring_boot_2.exceptions.UsuarioAutenticadoNaoEncontradoException;
import com.delegrego.exemplo_spring_boot_2.repo.DepartamentoRepository;
import com.delegrego.exemplo_spring_boot_2.repo.FuncionarioRepository;

@Service
//@RequiredArgsConstructor -> Pode ser utilizado em vez do construtor
public class FuncionarioService {

	// Maneira correta de acoplar camada de repositório
	private final FuncionarioRepository repo;

	private final DepartamentoRepository departamentoRepo;

	// A anotação de @Autowired é opcional
	// O Spring já faz a injeção automaticamente se:
	// A classe é um bean (@Service, por exemplo)
	// E a classe tem somente 1 construtor
	@Autowired
	public FuncionarioService(FuncionarioRepository repo, DepartamentoRepository departamentoRepo) {
		this.repo = repo;
		this.departamentoRepo = departamentoRepo;
	}

	@PreAuthorize("hasRole('GERENTE')")
	public FuncionarioEntity cadastrarFuncionario(FuncionarioCriarDto funcionarioDto) {

		if (repo.existsByEmail(funcionarioDto.getEmail())) {
			throw new EmailDuplicadoException("Usuário com esse email já existe");
		}

		if (repo.existsByCpf(funcionarioDto.getCpf())) {
			throw new CpfDuplicadoException("Usuário com esse CPF já existe");
		}

		FuncionarioEntity funcionarioEntity = new FuncionarioEntity();

		DepartamentoEntity departamentoEntity = departamentoRepo.findById(funcionarioDto.getIdDepartamento())
				.orElseThrow(() -> new DepartamentoNaoEncontradoException("Departamento não encontrado"));

		FuncionarioEntity criadoPor = obterUsuarioAutenticado();

		funcionarioEntity.setNome(funcionarioDto.getNome());
		funcionarioEntity.setCpf(funcionarioDto.getCpf());
		funcionarioEntity.setEmail(funcionarioDto.getEmail());
		funcionarioEntity.setSenha(funcionarioDto.getSenha());
		funcionarioEntity.setDataNascimento(funcionarioDto.getDataNascimento());
		funcionarioEntity.setSalario(funcionarioDto.getSalario());
		funcionarioEntity.setGerente(funcionarioDto.isGerente());

		funcionarioEntity.setEndereco(new EnderecoEntity());

		funcionarioEntity.getEndereco().setEstado(funcionarioDto.getEndereco().getEstado());
		funcionarioEntity.getEndereco().setCidade(funcionarioDto.getEndereco().getCidade());
		funcionarioEntity.getEndereco().setBairro(funcionarioDto.getEndereco().getBairro());
		funcionarioEntity.getEndereco().setLogradouro(funcionarioDto.getEndereco().getLogradouro());
		funcionarioEntity.getEndereco().setNumero(funcionarioDto.getEndereco().getNumero());
		funcionarioEntity.getEndereco().setCep(funcionarioDto.getEndereco().getCep());
		funcionarioEntity.setDepartamento(departamentoEntity);
		funcionarioEntity.setCriadoPor(criadoPor);

		return repo.save(funcionarioEntity);
	}

	@PreAuthorize("hasAnyRole('FUNCIONARIO', 'GERENTE')")
	public List<FuncionarioResponseDto> listarFuncionarios() {

		List<FuncionarioEntity> listaFuncionarioEntity = repo.findAll();

		List<FuncionarioResponseDto> listaFuncionarioDto = new ArrayList<>();

		for (FuncionarioEntity f : listaFuncionarioEntity) {

			FuncionarioResponseDto funcionarioDto = new FuncionarioResponseDto();

			funcionarioDto.setIdFuncionario(f.getIdFuncionario());
			funcionarioDto.setNome(f.getNome());
			funcionarioDto.setCpf(f.getCpf());
			funcionarioDto.setEmail(f.getEmail());
			funcionarioDto.setDataNascimento(f.getDataNascimento());
			funcionarioDto.setSalario(f.getSalario());
			funcionarioDto.setGerente(f.isGerente());

			funcionarioDto.setEndereco(new EnderecoResponseDto());
			funcionarioDto.getEndereco().setEstado(f.getEndereco().getEstado());
			funcionarioDto.getEndereco().setCidade(f.getEndereco().getCidade());
			funcionarioDto.getEndereco().setBairro(f.getEndereco().getBairro());
			funcionarioDto.getEndereco().setLogradouro(f.getEndereco().getLogradouro());
			funcionarioDto.getEndereco().setNumero(f.getEndereco().getNumero());
			funcionarioDto.getEndereco().setCep(f.getEndereco().getCep());

			funcionarioDto.setDepartamento(new DepartamentoResponseDto());
			funcionarioDto.getDepartamento().setIdDepartamento(f.getDepartamento().getIdDepartamento());
			funcionarioDto.getDepartamento().setNmDepartamento(f.getDepartamento().getNmDepartamento());

			if (f.getCriadoPor() != null) {
				funcionarioDto.setCriadoPor(f.getCriadoPor().getNome());
			}

			listaFuncionarioDto.add(funcionarioDto);
		}

		return listaFuncionarioDto;
	}

	@PreAuthorize("hasAnyRole('FUNCIONARIO', 'GERENTE')")
	public FuncionarioResponseDto obterFuncionarioPorId(BigInteger id) {

		FuncionarioEntity funcionarioEntity = repo.findById(id)
				.orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário não encontrado"));

		FuncionarioResponseDto funcionarioDto = new FuncionarioResponseDto();

		funcionarioDto.setIdFuncionario(funcionarioEntity.getIdFuncionario());
		funcionarioDto.setNome(funcionarioEntity.getNome());
		funcionarioDto.setCpf(funcionarioEntity.getCpf());
		funcionarioDto.setEmail(funcionarioEntity.getEmail());
		funcionarioDto.setDataNascimento(funcionarioEntity.getDataNascimento());
		funcionarioDto.setSalario(funcionarioEntity.getSalario());
		funcionarioDto.setGerente(funcionarioEntity.isGerente());

		funcionarioDto.setEndereco(new EnderecoResponseDto());
		funcionarioDto.getEndereco().setEstado(funcionarioEntity.getEndereco().getEstado());
		funcionarioDto.getEndereco().setCidade(funcionarioEntity.getEndereco().getCidade());
		funcionarioDto.getEndereco().setBairro(funcionarioEntity.getEndereco().getBairro());
		funcionarioDto.getEndereco().setLogradouro(funcionarioEntity.getEndereco().getLogradouro());
		funcionarioDto.getEndereco().setNumero(funcionarioEntity.getEndereco().getNumero());
		funcionarioDto.getEndereco().setCep(funcionarioEntity.getEndereco().getCep());

		funcionarioDto.setDepartamento(new DepartamentoResponseDto());
		funcionarioDto.getDepartamento().setIdDepartamento(funcionarioEntity.getDepartamento().getIdDepartamento());
		funcionarioDto.getDepartamento().setNmDepartamento(funcionarioEntity.getDepartamento().getNmDepartamento());

		if (funcionarioEntity.getCriadoPor() != null) {
			funcionarioDto.setCriadoPor(funcionarioEntity.getCriadoPor().getNome());
		}

		return funcionarioDto;
	}

	@PreAuthorize("hasAnyRole('FUNCIONARIO', 'GERENTE')")
	public List<FuncionarioResponseDto> pesquisarFuncionarios(String nome, String email, String cpf) {
		List<FuncionarioEntity> listaFuncionarioEntity = repo
				.findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCaseOrCpfContaining(nome, email, cpf);

		List<FuncionarioResponseDto> listaFuncionarioDto = new ArrayList<>();

		for (FuncionarioEntity f : listaFuncionarioEntity) {

			FuncionarioResponseDto funcionarioDto = new FuncionarioResponseDto();

			funcionarioDto.setIdFuncionario(f.getIdFuncionario());
			funcionarioDto.setNome(f.getNome());
			funcionarioDto.setCpf(f.getCpf());
			funcionarioDto.setEmail(f.getEmail());
			funcionarioDto.setDataNascimento(f.getDataNascimento());
			funcionarioDto.setSalario(f.getSalario());
			funcionarioDto.setGerente(f.isGerente());

			funcionarioDto.setEndereco(new EnderecoResponseDto());
			funcionarioDto.getEndereco().setEstado(f.getEndereco().getEstado());
			funcionarioDto.getEndereco().setCidade(f.getEndereco().getCidade());
			funcionarioDto.getEndereco().setBairro(f.getEndereco().getBairro());
			funcionarioDto.getEndereco().setLogradouro(f.getEndereco().getLogradouro());
			funcionarioDto.getEndereco().setNumero(f.getEndereco().getNumero());
			funcionarioDto.getEndereco().setCep(f.getEndereco().getCep());

			funcionarioDto.setDepartamento(new DepartamentoResponseDto());
			funcionarioDto.getDepartamento().setIdDepartamento(f.getDepartamento().getIdDepartamento());
			funcionarioDto.getDepartamento().setNmDepartamento(f.getDepartamento().getNmDepartamento());

			if (f.getCriadoPor() != null) {
				funcionarioDto.setCriadoPor(f.getCriadoPor().getNome());
			}

			listaFuncionarioDto.add(funcionarioDto);

		}

		return listaFuncionarioDto;
	}

	@PreAuthorize("hasRole('GERENTE')")
	public FuncionarioEntity atualizarFuncionario(BigInteger id, FuncionarioAtualizarDto funcionarioDto) {

		FuncionarioEntity funcionarioEntity = repo.findById(id)
				.orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário não encontrado"));

		if (repo.existsByEmailAndIdFuncionarioNot(funcionarioDto.getEmail(), id)) {
			throw new EmailDuplicadoException("Usuário com esse email já existe");
		}

		if (repo.existsByCpfAndIdFuncionarioNot(funcionarioDto.getCpf(), id)) {
			throw new CpfDuplicadoException("Usuário com esse CPF já existe");
		}

		DepartamentoEntity departamentoEntity = departamentoRepo.findById(funcionarioDto.getIdDepartamento())
				.orElseThrow(() -> new DepartamentoNaoEncontradoException("Departamento não encontrado"));

		funcionarioEntity.setNome(funcionarioDto.getNome());
		funcionarioEntity.setCpf(funcionarioDto.getCpf());
		funcionarioEntity.setEmail(funcionarioDto.getEmail());
		funcionarioEntity.setDataNascimento(funcionarioDto.getDataNascimento());
		funcionarioEntity.setSalario(funcionarioDto.getSalario());
		funcionarioEntity.setGerente(funcionarioDto.isGerente());

		funcionarioEntity.setEndereco(new EnderecoEntity());

		funcionarioEntity.getEndereco().setEstado(funcionarioDto.getEndereco().getEstado());
		funcionarioEntity.getEndereco().setCidade(funcionarioDto.getEndereco().getCidade());
		funcionarioEntity.getEndereco().setBairro(funcionarioDto.getEndereco().getBairro());
		funcionarioEntity.getEndereco().setLogradouro(funcionarioDto.getEndereco().getLogradouro());
		funcionarioEntity.getEndereco().setNumero(funcionarioDto.getEndereco().getNumero());
		funcionarioEntity.getEndereco().setCep(funcionarioDto.getEndereco().getCep());
		funcionarioEntity.setDepartamento(departamentoEntity);

		return repo.save(funcionarioEntity);
	}

	@PreAuthorize("hasRole('GERENTE')")
	public void deletarFuncionario(BigInteger id) {

		repo.findById(id).orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário não encontrado"));

		repo.deleteById(id);
	}

	/**
	 * Obtém o usuário autenticado no contexto de segurança do Spring Security.
	 * 
	 * @return A entidade do usuário autenticado.
	 */
	private FuncionarioEntity obterUsuarioAutenticado() {
		return repo.findByEmail(obterEmailUsuarioAutenticado())
				.orElseThrow(() -> new UsuarioAutenticadoNaoEncontradoException("Usuário não encontrado"));
	}

	/**
	 * Obtém o email do usuário autenticado no contexto de segurança do Spring
	 * Security.
	 * 
	 * @return O email do usuário autenticado.
	 */
	private String obterEmailUsuarioAutenticado() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
