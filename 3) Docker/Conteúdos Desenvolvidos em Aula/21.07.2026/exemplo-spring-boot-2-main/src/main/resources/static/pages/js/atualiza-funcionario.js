form.addEventListener("submit", async function (e) {
    e.preventDefault();

    const id = parseInt(form.id.value);

    const funcionario = {
        nome: form.nome.value,
        cpf: form.cpf.value,
        email: form.email.value,
        dataNascimento: form.dataNascimento.value,
        salario: parseFloat(form.salario.value) || 0,
        gerente: form.gerente.checked,
        endereco: {
            estado: form.estado.value,
            cidade: form.cidade.value,
            bairro: form.bairro.value,
            logradouro: form.logradouro.value,
            numero: form.numero.value,
            cep: form.cep.value,
        },
        idDepartamento: parseInt(form.departamento.value),
    };

    try {
        const res = await fetch(`/funcionarios/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(funcionario),
        });

        if (res.ok) {
            alert("Funcionário atualizado com sucesso!");
            window.location.href = "lista-funcionarios.html";
        } else {
            const erro = await res.text();
            alert("Erro ao atualizar: " + erro);
        }
    } catch (erro) {
        alert("Erro de rede: " + erro.message);
    }
});