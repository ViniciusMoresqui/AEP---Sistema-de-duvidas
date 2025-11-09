package com.AEP4.SistemaAEP.Repository;

import com.AEP4.SistemaAEP.Model.MateriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<MateriaModel, Long> {
}