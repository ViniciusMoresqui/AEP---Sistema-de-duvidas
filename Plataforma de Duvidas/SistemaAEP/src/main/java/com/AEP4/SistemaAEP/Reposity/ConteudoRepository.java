package com.AEP4.SistemaAEP.Repository;

import com.AEP4.SistemaAEP.Model.ConteudoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConteudoRepository extends JpaRepository<ConteudoModel, Long> {

    List<ConteudoModel> findByMateriaId(Long materiaId);
}