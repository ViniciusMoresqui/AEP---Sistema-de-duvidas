package com.AEP4.SistemaAEP.Controller;

import com.AEP4.SistemaAEP.Model.ConteudoModel;
import com.AEP4.SistemaAEP.Service.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/conteudos")
@CrossOrigin(origins = "*")
public class ConteudoController {

    @Autowired
    private ConteudoService conteudoService;

    @GetMapping
    public ResponseEntity<List<ConteudoModel>> listarConteudos(
            @RequestParam(required = false) Long materiaId
    ) {
        List<ConteudoModel> conteudos = conteudoService.listarTodos(materiaId);
        return ResponseEntity.ok(conteudos);
    }
}