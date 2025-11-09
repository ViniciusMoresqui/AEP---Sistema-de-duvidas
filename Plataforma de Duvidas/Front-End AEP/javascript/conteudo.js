const API_DUVIDAS = 'http://localhost:8090/api/duvidas';
const API_MATERIAS = 'http://localhost:8090/api/materias';
const API_USUARIOS = 'http://localhost:8090/api/usuarios';
const API_COMENTARIOS = 'http://localhost:8090/api/comentarios';


const formNovaDuvida = document.getElementById('form-nova-duvida');
const selectMateria = document.getElementById('select-materia');
const nomeUsuarioSpan = document.getElementById('nome-usuario');
const listaMinhasDuvidas = document.getElementById('lista-minhas-duvidas');

let currentUserId = null; 

async function popularMaterias() {
    try {
        const response = await fetch(API_MATERIAS);
        const materias = await response.json();
        selectMateria.innerHTML = ''; 
        
        const defaultOption = document.createElement('option');
        defaultOption.value = "";
        defaultOption.textContent = "Selecione a matéria";
        defaultOption.disabled = true;
        defaultOption.selected = true;
        selectMateria.appendChild(defaultOption);

        materias.forEach(item => {
            const option = document.createElement('option');
            option.value = item.id;
            option.textContent = item.nome;
            selectMateria.appendChild(option);
        });
    } catch (error) {
        console.error('Erro ao buscar matérias:', error);
    }
}


async function handleCriarDuvida(event) {
    event.preventDefault();
    
    const titulo = document.getElementById('titulo').value;
    const descricao = document.getElementById('descricao').value;
    const materiaId = selectMateria.value;

    if (!materiaId) {
        alert('Selecione uma matéria.');
        return;
    }

    const dadosDuvida = {
        titulo: titulo,
        descricao: descricao,
        autor: { id: currentUserId },
        materia: { id: parseInt(materiaId) }
    };

    try {
        const response = await fetch(API_DUVIDAS, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dadosDuvida)
        });

        if (response.status === 201) {
            alert('Dúvida enviada com sucesso!');
            formNovaDuvida.reset();
            selectMateria.selectedIndex = 0;
            carregarDuvidasComunidade(); 
        } else {
            alert('Erro ao criar dúvida.');
        }
    } catch (error) {
        alert('Erro de rede ao criar dúvida.');
    }
}


async function carregarDadosUsuario(userId) {
    try {
        const response = await fetch(`${API_USUARIOS}/${userId}`);
        const usuario = await response.json();
        nomeUsuarioSpan.textContent = usuario.nome;
    } catch (error) {
        nomeUsuarioSpan.textContent = "Aluno(a)";
    }
}



async function carregarDuvidasComunidade() {
    if (!currentUserId) return;
    
    listaMinhasDuvidas.innerHTML = '<li>Carregando dúvidas da comunidade...</li>';
    

    try {
        const responseDuvidas = await fetch(API_DUVIDAS);
        const duvidas = await responseDuvidas.json();

        listaMinhasDuvidas.innerHTML = ''; // Limpa
        if (duvidas.length === 0) {
            listaMinhasDuvidas.innerHTML = '<li>Nenhuma dúvida na comunidade ainda.</li>';
            return;
        }


        for (const duvida of duvidas) {
            const itemDuvida = document.createElement('li');
            itemDuvida.className = 'duvida-item';
            
    
            const responseComentarios = await fetch(`${API_COMENTARIOS}?duvidaId=${duvida.id}`);
            const comentarios = await responseComentarios.json();

            let comentariosHtml = '<ul class="lista-comentarios">';
            if (comentarios.length === 0) {
                comentariosHtml += '<li class="comentario-item-nenhum">Nenhuma resposta ainda.</li>';
            } else {
                comentarios.forEach(comentario => {
                    const tipoAutor = comentario.autor.tipo === 'MONITOR' ? 'Monitor' : 'Aluno';
                    comentariosHtml += `
                        <li class="comentario-item">
                            <strong>${comentario.autor.nome} (${tipoAutor}):</strong>
                            <p>${comentario.texto}</p>
                        </li>
                    `;
                });
            }
            comentariosHtml += '</ul>';

         
            itemDuvida.innerHTML = `
                <strong>${duvida.titulo}</strong> (Status: ${duvida.status})<br>
                <small>Autor: ${duvida.autor.nome} | Matéria: ${duvida.materia.nome}</small>
                <p>${duvida.descricao}</p>
                
                <h4>Respostas:</h4>
                ${comentariosHtml}
                
                <form class="form-resposta" data-duvida-id="${duvida.id}">
                    <textarea placeholder="Adicione sua resposta..." required></textarea>
                    <button type="submit" class="btn-responder">Enviar Resposta</button>
                </form>
            `;
            listaMinhasDuvidas.appendChild(itemDuvida);
        }
        
   
        document.querySelectorAll('.form-resposta').forEach(form => {
            form.addEventListener('submit', handleEnviarResposta);
        });

    } catch (error) {
        console.error('Erro ao carregar dúvidas:', error);
        listaMinhasDuvidas.innerHTML = '<li>Erro ao carregar dúvidas.</li>';
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
            alert('Resposta enviada com sucesso!');
            carregarDuvidasComunidade(); 
        } else {
            alert('Erro ao enviar resposta.');
        }
    } catch (error) {
        alert('Erro de rede ao enviar resposta.');
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
    popularMaterias();
    carregarDuvidasComunidade(); 
    
    formNovaDuvida.addEventListener('submit', handleCriarDuvida);
});