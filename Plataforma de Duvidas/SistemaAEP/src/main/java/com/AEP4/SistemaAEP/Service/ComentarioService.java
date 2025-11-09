package com.AEP4.SistemaAEP.Service;

import com.AEP4.SistemaAEP.Model.*;
import com.AEP4.SistemaAEP.Repository.ComentarioRepository;
import com.AEP4.SistemaAEP.Repository.DuvidaRepository;
import com.AEP4.SistemaAEP.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List; // Não se esqueça de importar List!

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DuvidaRepository duvidaRepository;

    public ComentarioModel criarComentario(ComentarioModel comentario) {

        UsuarioModel autorReal = usuarioRepository.findById(comentario.getAutor().getId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        DuvidaModel duvidaReal = duvidaRepository.findById(comentario.getDuvida().getId())
                .orElseThrow(() -> new RuntimeException("Dúvida não encontrada"));

        comentario.setAutor(autorReal);
        comentario.setDuvida(duvidaReal);


        if (autorReal.getTipo() == PerfilUsuario.MONITOR) {
            duvidaReal.setStatus(DuvidaStatus.RESOLVIDA);
            duvidaRepository.save(duvidaReal);
        }

        return comentarioRepository.save(comentario);
    }

    public List<ComentarioModel> buscarPorDuvida(Long duvidaId) {
        return comentarioRepository.findByDuvidaId(duvidaId);
    }
}