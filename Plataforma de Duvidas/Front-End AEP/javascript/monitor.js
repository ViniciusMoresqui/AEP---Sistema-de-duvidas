
const API_DUVIDAS = 'http://localhost:8090/api/duvidas';
const API_USUARIOS = 'http://localhost:8090/api/usuarios';
const API_COMENTARIOS = 'http://localhost:8090/api/comentarios';

const listaDuvidasMonitor = document.getElementById('lista-duvidas-monitor');
const nomeUsuarioSpan = document.getElementById('nome-usuario');

let currentUserId = null; 


async function carregarDuvidasMonitor() {
    listaDuvidasMonitor.innerHTML = '<li>Carregando dúvidas pendentes...</li>';
    
    try {
       
        const response = await fetch(API_DUVIDAS);
        const duvidas = await response.json();
        
        listaDuvidasMonitor.innerHTML = '';
        
        if (duvidas.length === 0) {
            listaDuvidasMonitor.innerHTML = '<li>Nenhuma dúvida pendente. Bom trabalho!</li>';
            return;
        }

        duvidas.forEach(duvida => {
            const item = document.createElement('li');
            item.setAttribute('data-status', duvida.status);
        
            item.innerHTML = `
                <strong>${duvida.titulo}</strong> (Status: ${duvida.status})<br>
                <small>Autor: ${duvida.autor.nome} | Matéria: ${duvida.materia.nome}</small>
                <p>${duvida.descricao}</p>
                
                <form class="form-resposta" data-duvida-id="${duvida.id}">
                    <textarea placeholder="Escreva sua resposta aqui..." required></textarea>
                    <button type="submit" class="btn-responder">Enviar Resposta e Resolver</button>
                </form>
            `;
            
            listaDuvidasMonitor.appendChild(item);
        });

        document.querySelectorAll('.form-resposta').forEach(form => {
            form.addEventListener('submit', handleEnviarResposta);
        });

    } catch (error) {
        listaDuvidasMonitor.innerHTML = '<li>Erro ao carregar dúvidas.</li>';
    }
}

async function handleEnviarResposta(event) {
    event.preventDefault(); 
    
    const form = event.target;
    const duvidaId = form.dataset.duvidaId;
    const textarea = form.querySelector('textarea');
    const textoResposta = textarea.value;

    if (!textoResposta.trim()) {
        alert('Escreva uma resposta.');
        return;
    }

    const dadosComentario = {
        texto: textoResposta,
        autor: { id: currentUserId },
        duvida: { id: parseInt(duvidaId) }
    };

    try {
        const response = await fetch(API_COMENTARIOS, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dadosComentario)
        });

        if (response.status === 201) {
            alert('Resposta enviada e dúvida resolvida!');

            carregarDuvidasMonitor(); 
        } else {
            alert('Erro ao enviar resposta.');
        }
    } catch (error) {
        alert('Erro de rede ao enviar resposta.');
    }
}


async function carregarDadosUsuario(userId) {
    try {
        const response = await fetch(`${API_USUARIOS}/${userId}`);
        const usuario = await response.json();
        nomeUsuarioSpan.textContent = usuario.nome;
    } catch (error) {
        nomeUsuarioSpan.textContent = "Monitor(a)";
    }
}


document.addEventListener('DOMContentLoaded', () => {
    const params = new URLSearchParams(window.location.search);
    const userId = params.get('userId');

    if (!userId) {
        alert('Nenhum usuário logado. Redirecionando para o login.');
        window.location.href = 'login.html';
        return;
    }

    currentUserId = parseInt(userId);
    carregarDadosUsuario(currentUserId);
    carregarDuvidasMonitor();
});