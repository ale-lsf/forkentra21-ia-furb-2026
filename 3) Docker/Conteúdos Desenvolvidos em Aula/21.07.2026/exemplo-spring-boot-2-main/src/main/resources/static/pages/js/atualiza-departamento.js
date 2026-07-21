
document.getElementById('form-editar').onsubmit = function (e) {
    e.preventDefault();

    const id = document.getElementById('idDepartamento').value;

    fetch(`/departamentos/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            nmDepartamento: document.getElementById('nome').value
        })
    }).then(resp => {
        if (resp.ok) {
            alert('Departamento atualizado!');
            window.location.href = '../html/lista-departamentos.html';
        } else {
            alert('Erro ao atualizar departamento.');
        }
    });
};