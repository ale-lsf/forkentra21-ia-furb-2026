async function carregarDepartamentos() {
    try {
        const response = await fetch('/departamentos');
        if (!response.ok) throw new Error('Erro ao buscar departamentos');

        const departamentos = await response.json();
        const select = document.getElementById('departamentos');

        departamentos.forEach(dep => {
            const option = document.createElement('option');
            option.value = dep.idDepartamento;
            option.textContent = dep.nmDepartamento;
            select.appendChild(option);
        });
    } catch (error) {
        alert('Erro ao carregar departamentos: ' + error.message);
    }
}

carregarDepartamentos();