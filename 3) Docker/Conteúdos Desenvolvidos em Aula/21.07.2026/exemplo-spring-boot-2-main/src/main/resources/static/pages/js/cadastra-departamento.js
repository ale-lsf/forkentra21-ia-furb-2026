document.getElementById('formDepartamento').addEventListener('submit', async function (e) {
    e.preventDefault();

    const form = e.target;
    const departamento = {
        nmDepartamento: form.nmDepartamento.value.trim()
    };

    try {
        const response = await fetch('/departamentos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(departamento)
        });

        if (response.ok) {
            alert('Departamento cadastrado com sucesso!');
            form.reset();
        } else {
            const errorText = await response.text();
            alert('Erro ao cadastrar: ' + errorText);
        }
    } catch (error) {
        alert('Erro de rede: ' + error.message);
    }
});