const form = document.getElementById("formEdicaoFuncionario");
const params = new URLSearchParams(window.location.search);
const funcionarioId = params.get("id");

async function carregarDepartamentosSelecionaveis(departamentoAtualId) {
    const select = document.getElementById("departamentosSelect");

    try {
        const res = await fetch("/departamentos");
        const departamentos = await res.json();

        departamentos.forEach(dep => {
            const option = document.createElement("option");
            option.value = dep.idDepartamento;
            option.textContent = dep.nmDepartamento;
            if (dep.idDepartamento === departamentoAtualId) {
                option.selected = true;
            }
            select.appendChild(option);
        });
    } catch (err) {
        alert("Erro ao carregar departamentos: " + err.message);
    }
}

async function carregarFuncionario() {
    try {
        const response = await fetch(`/funcionarios/${funcionarioId}`);
        if (!response.ok) throw new Error("Funcionário não encontrado");

        const func = await response.json();
        form.id.value = func.idFuncionario;
        form.criadoPor.value = func.criadoPor?.idFuncionario || "";
        form.nome.value = func.nome;
        form.cpf.value = func.cpf;
        form.email.value = func.email;
        form.senha.value = func.senha;
        form.dataNascimento.value = func.dataNascimento;
        form.salario.value = func.salario;
        form.gerente.checked = func.gerente;

        form.estado.value = func.endereco?.estado || "";
        form.cidade.value = func.endereco?.cidade || "";
        form.bairro.value = func.endereco?.bairro || "";
        form.logradouro.value = func.endereco?.logradouro || "";
        form.numero.value = func.endereco?.numero || "";
        form.cep.value = func.endereco?.cep || "";

        await carregarDepartamentosSelecionaveis(func.departamento?.idDepartamento);

    } catch (error) {
        alert("Erro ao carregar funcionário: " + error.message);
    }
}

carregarFuncionario();