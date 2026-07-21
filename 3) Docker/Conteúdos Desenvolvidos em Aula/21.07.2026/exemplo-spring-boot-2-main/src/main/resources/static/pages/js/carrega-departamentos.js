async function carregarDepartamentos() {
    try {
        const response = await fetch('/departamentos');
        const departamentos = await response.json();

        const lista = document.getElementById('departamentos');
        lista.innerHTML = '';

        departamentos.forEach(departamento => {
            const div = document.createElement('div');
            div.className = 'departamento';
            div.innerHTML = `
                <span><strong>Nome:</strong> ${departamento.nmDepartamento}</span>
                <span><strong>ID:</strong> ${departamento.idDepartamento}</span>
                <div class="acoes">
                    <button class="btn-editar" title="Editar" onclick="editarDepartamento(${departamento.idDepartamento})">✏️</button>
                    <button class="btn-excluir" title="Excluir" onclick="confirmarExclusao(${departamento.idDepartamento})">❌</button>
                </div>
            `;
            lista.appendChild(div);
        });

        ocultarAcoesSeNaoGerente();
    } catch (error) {
        console.error('Erro ao carregar departamentos:', error);
    }
}

async function ocultarAcoesSeNaoGerente() {
    try {
        const response = await fetch('/funcionarios/me/gerente');
        const funcionario = await response.json();

        if (!funcionario.gerente) {
            const acoesElements = document.querySelectorAll('.acoes');
            acoesElements.forEach(element => {
                element.style.display = "none";
            });
        }
    } catch (error) {
        console.error('Erro ao verificar se o usuário é gerente:', error);
    }
}

function editarDepartamento(idDepartamento) {
    window.location.href = `edicao-departamento.html?id=${idDepartamento}`;
}

async function confirmarExclusao(idDepartamento) {
    const confirmacao = confirm("Tem certeza que deseja excluir este departamento?");
    if (!confirmacao) return;

    try {
        const resposta = await fetch(`/departamentos/${idDepartamento}`, {
            method: 'DELETE'
        });

        if (resposta.ok) {
            window.location.reload();
        } else {
            const erro = await resposta.text();
            alert("Erro ao excluir: " + erro);
        }
    } catch (erro) {
        alert("Erro de rede ao excluir: " + erro.message);
    }
}

carregarDepartamentos();