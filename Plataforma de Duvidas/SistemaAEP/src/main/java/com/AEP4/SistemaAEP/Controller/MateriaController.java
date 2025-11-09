package com.AEP4.SistemaAEP.Controller;

import com.AEP4.SistemaAEP.Model.MateriaModel;
import com.AEP4.SistemaAEP.Service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
@CrossOrigin(origins = "*")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaModel>> listarMaterias() {
        List<MateriaModel> materias = materiaService.listarTodas();
        return ResponseEntity.ok(materias);
    }
}