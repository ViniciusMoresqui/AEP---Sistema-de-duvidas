package com.AEP4.SistemaAEP.Controller;

import com.AEP4.SistemaAEP.Model.DuvidaModel;
import com.AEP4.SistemaAEP.Service.DuvidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duvidas")
@CrossOrigin(origins = "*")

public class DuvidaController {
    @Autowired
    private DuvidaService duvidaService;

    @PostMapping
    public ResponseEntity<DuvidaModel> criarDuvida(@RequestBody DuvidaModel duvida) {
        DuvidaModel novaDuvida = duvidaService.criarDuvida(duvida);
        return new ResponseEntity<>(novaDuvida, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DuvidaModel> buscarDuvida(@PathVariable Long id) {
        return duvidaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/resolver")
    public ResponseEntity<DuvidaModel> resolverDuvida(@PathVariable Long id) {

        return duvidaService.resolverDuvida(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DuvidaModel>> listarDuvidas(
            @RequestParam(required = false) Long materiaId,
            @RequestParam(required = false) Long autorId
    ) {
        List<DuvidaModel> duvidas = duvidaService.buscarFiltradas(materiaId, autorId);
        return ResponseEntity.ok(duvidas);
    }
}