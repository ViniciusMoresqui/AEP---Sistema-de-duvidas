package com.AEP4.SistemaAEP.Service;

import com.AEP4.SistemaAEP.Model.ConteudoModel;
import com.AEP4.SistemaAEP.Repository.ConteudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConteudoService {

    @Autowired
    private ConteudoRepository conteudoRepository;

    public List<ConteudoModel> listarTodos(Long materiaId) {
        if (materiaId != null) {
            return conteudoRepository.findByMateriaId(materiaId);
        }
        return conteudoRepository.findAll();
    }
}