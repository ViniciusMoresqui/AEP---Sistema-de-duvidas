package com.AEP4.SistemaAEP.Repository;

import com.AEP4.SistemaAEP.Model.ComentarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioModel, Long> {


    List<ComentarioModel> findByDuvidaId(Long duvidaId);

}