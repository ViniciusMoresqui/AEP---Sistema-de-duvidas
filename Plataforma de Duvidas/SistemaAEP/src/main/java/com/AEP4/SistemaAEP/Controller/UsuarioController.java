package com.AEP4.SistemaAEP.Controller;

import com.AEP4.SistemaAEP.Model.LoginRequest; // IMPORTAR
import com.AEP4.SistemaAEP.Model.UsuarioModel;
import com.AEP4.SistemaAEP.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioModel> cadastrar(@RequestBody UsuarioModel novoUsuario) {
        UsuarioModel usuarioSalvo = usuarioService.cadastrarUsuario(novoUsuario);
        return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<UsuarioModel> login(@RequestBody LoginRequest loginRequest) {
        try {
            UsuarioModel usuario = usuarioService.autenticar(loginRequest);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        List<UsuarioModel> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> buscarUsuario(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}