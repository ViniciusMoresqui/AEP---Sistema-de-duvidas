package com.AEP4.SistemaAEP.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_MATERIA")
@Getter
@Setter
public class MateriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    public MateriaModel() {
    }
}