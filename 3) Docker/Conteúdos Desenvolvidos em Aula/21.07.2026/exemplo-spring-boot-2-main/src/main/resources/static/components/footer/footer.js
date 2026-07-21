fetch('../../components/footer/footer.html')
    .then(response => response.text())
    .then(footer => {
        document.getElementById('footer').innerHTML = footer;
        const link = document.createElement('link');
        link.rel = 'stylesheet';
        link.href = '../../components/footer/footer-style.css';
        document.head.appendChild(link);
    })
    .catch(error => {
        document.getElementById('footer').innerHTML = `<p>Erro ao carregar o header</p>`;
    });