const API_CADASTRO = 'http://localhost:8090/api/usuarios/cadastro';
const formCadastro = document.getElementById('form-cadastro');

async function handleCadastro(event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;
    const tipo = document.getElementById('tipo').value;

    if (!tipo) {
        alert('Por favor, selecione se você é Estudante ou Monitor.');
        return;
    }

    const dadosCadastro = {
        nome: nome,
        email: email,
        senhaHash: senha, 
        tipo: tipo
    };

    try {
        const response = await fetch(API_CADASTRO, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dadosCadastro)
        });

        if (response.status === 201) { 
            alert('Cadastro realizado com sucesso! Você será redirecionado para o login.');
            window.location.href = 'login.html';
        } else {
            alert('Erro ao cadastrar. Verifique se o e-mail já está em uso.');
        }
    } catch (error) {
        alert('Erro de rede. A API está rodando?');
    }
}

document.addEventListener('DOMContentLoaded', () => {
    formCadastro.addEventListener('submit', handleCadastro);
});