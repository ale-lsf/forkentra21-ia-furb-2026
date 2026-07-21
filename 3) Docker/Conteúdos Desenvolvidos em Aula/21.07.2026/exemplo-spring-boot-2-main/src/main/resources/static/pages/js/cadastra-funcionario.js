
async function cadastrarFuncionario() {
    document.getElementById('formFuncionario').addEventListener('submit', async function (e) {
        e.preventDefault();

        const form = e.target;

        try {
            const funcionario = {
                nome: form.nome.value,
                cpf: form.cpf.value,
                email: form.email.value,
                senha: form.senha.value,
                dataNascimento: form.dataNascimento.value,
                salario: parseFloat(form.salario.value) || 0,
                gerente: form.gerente.checked,
                endereco: {
                    estado: form.estado.value,
                    cidade: form.cidade.value,
                    bairro: form.bairro.value,
                    logradouro: form.logradouro.value,
                    numero: form.numero.value,
                    cep: form.cep.value
                },
                idDepartamento: parseInt(form.departamento.value)
            };

            const response = await fetch('/funcionarios', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(funcionario)
            });

            if (response.ok) {
                alert('Funcionário cadastrado com sucesso!');
                form.reset();
            } else {
                const errorText = await response.text();
                alert('Erro ao cadastrar: ' + errorText);
            }
        } catch (error) {
            alert('Erro ao cadastrar funcionário: ' + error.message);
        }
    });
}

cadastrarFuncionario();