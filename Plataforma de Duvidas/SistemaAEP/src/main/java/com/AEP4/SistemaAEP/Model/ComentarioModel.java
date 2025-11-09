package com.AEP4.SistemaAEP.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_COMENTARIO")
@Getter
@Setter
public class ComentarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String texto;

    private LocalDateTime dataCriacao = LocalDateTime.now();


    @ManyToOne
    @JoinColumn(name = "duvida_id", nullable = false)
    private DuvidaModel duvida;


    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel autor;

    public ComentarioModel() {
    }
}