package com.AEP4.SistemaAEP.Service;

import com.AEP4.SistemaAEP.Model.MateriaModel;
import com.AEP4.SistemaAEP.Repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    public List<MateriaModel> listarTodas() {
        return materiaRepository.findAll();
    }
}