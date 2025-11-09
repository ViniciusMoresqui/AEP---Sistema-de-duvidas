package com.AEP4.SistemaAEP.Service;

import com.AEP4.SistemaAEP.Model.LoginRequest; // IMPORTAR
import com.AEP4.SistemaAEP.Model.UsuarioModel;
import com.AEP4.SistemaAEP.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioModel cadastrarUsuario(UsuarioModel usuario) {

        return usuarioRepository.save(usuario);
    }


    public UsuarioModel autenticar(LoginRequest loginRequest) {

        Optional<UsuarioModel> optUsuario = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (optUsuario.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        UsuarioModel usuario = optUsuario.get();


        if (verificarSenha(loginRequest.getSenha(), usuario.getSenhaHash())) {
            return usuario;
        } else {
            throw new RuntimeException("Senha incorreta.");
        }
    }


    private boolean verificarSenha(String senhaPura, String senhaHash) {
        return senhaPura.equals(senhaHash);
    }



    public Optional<UsuarioModel> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<UsuarioModel> listarTodos() {
        return usuarioRepository.findAll();
    }
}