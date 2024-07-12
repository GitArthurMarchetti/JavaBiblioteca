document.getElementById('editStudentForm').addEventListener('submit', function(event) {
    event.preventDefault();
  
    const nome = document.getElementById('nome').value;
    const matricula = document.getElementById('matricula').value;
    const email = document.getElementById('email').value;
    const telefone = document.getElementById('telefone').value;
    const senha = document.getElementById('password').value;
    const messageElement = document.getElementById('messageEst');

    //axios.post('http://localhost:8020/biblio/estudante/login', {
    axios.post('http://localhost:8020/biblio/estudante', {
        nome: nome,
        matricula: matricula,
        email: email,
        telefone: telefone,
        senha: senha
    })
    .then(function (response) {
        messageElement.style.color = 'green';
        messageElement.textContent = 'Estudante salvo com sucesso!';
        document.getElementById('but-clear-form-estudante').click();
        console.log("Resposta cadastro estudante: " + response.data);
        setTimeout(function() {
            messageElement.textContent = '';
          }, 3000);
    })
    .catch(function (error) {
        messageElement.style.color = 'red';
        messageElement.textContent = 'Erro ao salvar estudante.';
        console.error(error);
    });
});

document.getElementById("estudantebar").addEventListener('click', function() {

    axios.get('http://localhost:8020/biblio/estudante/pag/1')
        .then(function (response) {
            const students = response.data;
            const tbody = document.querySelector('#studentsTable tbody');
            students.forEach(student => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${student.nome}</td>
                    <td>${student.matricula}</td>
                    <td>${student.email}</td>
                    <td>${student.telefone}</td>
                    <td><a href="#"><img src="./images/cancel.png" title="Excluir Estudante"
                                      onclick="excluirEstudante(${student.idEstudante});"></a>
                `;
                tbody.appendChild(row);

            });
        })
        .catch(function (error) {
            console.error('Erro ao carregar a lista de estudantes:', error);
        });
});

function excluirEstudante(idEstudante){

    axios.delete('http://localhost:8020/biblio/estudante/id/'+ idEstudante)
        .then(function (response) {
            if(response.status === 200){
                alert("Estudante, id:" + idEstudante + " excluído com sucesso!");
                const bodySection = document.querySelectorAll("tbody")[0];
                const rows = bodySection.rows;
                var linhas = rows.length;
                for (var x=1;x<=linhas; x++) {
                    bodySection.deleteRow(-1);
                    console.log("Excluiu linha: " + x);
                }
                document.getElementById("estudantebar").click();
                return;
            } else {
                alert("Erro ao excluir Estudante com id:" + idEstudante);
                return;
            };


        })
        .catch(function (error) {
            console.error('Erro ao carregar a lista de estudantes:', error);
            alert("Erro no servidor/url ao excluir Estudante, verifique... " + error);
            return;
        });


}