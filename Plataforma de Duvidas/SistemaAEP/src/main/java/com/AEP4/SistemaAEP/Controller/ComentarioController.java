package com.AEP4.SistemaAEP.Controller;

import com.AEP4.SistemaAEP.Model.ComentarioModel;
import com.AEP4.SistemaAEP.Service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping
    public ResponseEntity<ComentarioModel> criarComentario(@RequestBody ComentarioModel comentario) {
        ComentarioModel novoComentario = comentarioService.criarComentario(comentario);
        return new ResponseEntity<>(novoComentario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ComentarioModel>> listarComentarios(
            @RequestParam Long duvidaId
    ) {
        List<ComentarioModel> comentarios = comentarioService.buscarPorDuvida(duvidaId);
        return ResponseEntity.ok(comentarios);
    }
}