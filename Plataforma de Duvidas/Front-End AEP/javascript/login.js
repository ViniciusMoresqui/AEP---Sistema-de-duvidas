const API_LOGIN = 'http://localhost:8090/api/usuarios/login';
const formLogin = document.getElementById('form-login');

async function handleLogin(event) {
    event.preventDefault();
    
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;


    const dadosLogin = {
        email: email,
        senha: senha
    };

    try {
        const response = await fetch(API_LOGIN, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dadosLogin)
        });

        if (response.ok) { 
            const user = await response.json(); 

      
            if (user.tipo === 'ESTUDANTE') {
                window.location.href = `aluno.html?userId=${user.id}`;
            } else if (user.tipo === 'MONITOR') {
                window.location.href = `monitor.html?userId=${user.id}`;
            }
        } else {

            alert('E-mail ou senha incorretos.');
        }
    } catch (error) {
        alert('Erro de rede. A API está rodando?');
    }
}

document.addEventListener('DOMContentLoaded', () => {
    formLogin.addEventListener('submit', handleLogin);
});