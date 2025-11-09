package com.AEP4.SistemaAEP.Repository;

import com.AEP4.SistemaAEP.Model.DuvidaModel;
import com.AEP4.SistemaAEP.Model.DuvidaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DuvidaRepository extends JpaRepository<DuvidaModel, Long> {

    List<DuvidaModel> findByMateriaId(Long materiaId);
    List<DuvidaModel> findByAutorId(Long autorId);


    List<DuvidaModel> findByStatusNot(DuvidaStatus status);


    List<DuvidaModel> findByMateriaIdAndStatusNot(Long materiaId, DuvidaStatus status);
}