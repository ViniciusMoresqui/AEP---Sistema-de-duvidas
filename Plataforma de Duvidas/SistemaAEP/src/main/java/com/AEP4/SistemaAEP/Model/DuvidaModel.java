package com.AEP4.SistemaAEP.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_DUVIDA")
@Getter
@Setter
public class DuvidaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DuvidaStatus status = DuvidaStatus.ABERTA;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel autor;


    @ManyToOne
    @JoinColumn(name = "materia_id", nullable = false)
    private MateriaModel materia;

    public DuvidaModel() {}
}