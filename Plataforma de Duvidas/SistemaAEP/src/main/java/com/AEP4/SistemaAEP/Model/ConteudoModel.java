package com.AEP4.SistemaAEP.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_CONTEUDO")
@Getter
@Setter
public class ConteudoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private String urlRecurso;

    @ManyToOne
    @JoinColumn(name = "materia_id", nullable = false)
    private MateriaModel materia;

    public ConteudoModel() {
    }
}