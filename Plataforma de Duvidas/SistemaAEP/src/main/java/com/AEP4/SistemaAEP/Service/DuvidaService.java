package com.AEP4.SistemaAEP.Service;

import com.AEP4.SistemaAEP.Model.DuvidaModel;
import com.AEP4.SistemaAEP.Model.DuvidaStatus;
import com.AEP4.SistemaAEP.Model.MateriaModel;
import com.AEP4.SistemaAEP.Model.UsuarioModel;
import com.AEP4.SistemaAEP.Repository.DuvidaRepository;
import com.AEP4.SistemaAEP.Repository.MateriaRepository;
import com.AEP4.SistemaAEP.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DuvidaService {
    @Autowired
    private DuvidaRepository duvidaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MateriaRepository materiaRepository;

    public DuvidaModel criarDuvida(DuvidaModel duvida) {

        UsuarioModel autorReal = usuarioRepository.findById(duvida.getAutor().getId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID: " + duvida.getAutor().getId()));
        MateriaModel materiaReal = materiaRepository.findById(duvida.getMateria().getId())
                .orElseThrow(() -> new RuntimeException("Matéria não encontrada com ID: " + duvida.getMateria().getId()));
        duvida.setAutor(autorReal);
        duvida.setMateria(materiaReal);
        duvida.setStatus(DuvidaStatus.ABERTA);
        return duvidaRepository.save(duvida);
    }

    public Optional<DuvidaModel> buscarPorId(Long id) {
        return duvidaRepository.findById(id);
    }

    public Optional<DuvidaModel> resolverDuvida(Long id) {
        return duvidaRepository.findById(id).map(duvida -> {
            duvida.setStatus(DuvidaStatus.RESOLVIDA);
            return duvidaRepository.save(duvida);
        });
    }

    public List<DuvidaModel> buscarFiltradas(Long materiaId, Long autorId) {

        if (autorId != null) {
            return duvidaRepository.findByAutorId(autorId);
        }


        if (materiaId != null) {

            return duvidaRepository.findByMateriaIdAndStatusNot(materiaId, DuvidaStatus.RESOLVIDA);
        }

        return duvidaRepository.findByStatusNot(DuvidaStatus.RESOLVIDA);
    }



}