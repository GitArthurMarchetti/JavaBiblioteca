document.getElementById('cadastroForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const matricula = document.getElementById('matricula').value;
    const email = document.getElementById('email').value;
    const telefone = document.getElementById('telefone').value;
    const senha = document.getElementById('password').value;
    const idEstud = document.getElementById('idEstud').value;
    const messageElement = document.getElementById('messageEst');
    const statform = document.getElementById('ctrl-form-est').value;
    var acao = "post";

    axios({
        method: acao,
        url: 'http://localhost:8020/biblio/logim',
        data: {
            nome: nome,
            matricula: matricula,
            email: email,
            telefone: telefone,
            senha: senha,
            idEstudante: idEstud
        }
    })
    .then(function (response) {
        messageElement.style.color = 'green';
        messageElement.textContent = 'Cadastro salvo com sucesso!';
        ativaModal('Cadastro salvo com sucesso! ' + response.data);
        document.getElementById('but-clear-form-estudante').click();
        console.log("Resposta cadastro estudante: " + response.data);
        setTimeout(function () {
            messageElement.textContent = '';
        }, 3000);
    })
    .catch(function (error) {
        messageElement.style.color = 'red';
        messageElement.textContent = 'Erro ao cadastrar.';
        ativaModal('Erro ao cadastrar.');
        console.error(error);
    });
});

