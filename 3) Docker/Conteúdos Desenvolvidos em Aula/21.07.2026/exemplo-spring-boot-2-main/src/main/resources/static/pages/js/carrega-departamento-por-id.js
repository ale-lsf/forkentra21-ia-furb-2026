// Pega o ID da URL
const params = new URLSearchParams(window.location.search);
const id = params.get('id');
// Carrega dados do departamento
fetch(`/departamentos/${id}`)
    .then(res => res.json())
    .then(dep => {
        document.getElementById('idDepartamento').value = dep.idDepartamento;
        document.getElementById('nome').value = dep.nmDepartamento;
    });
