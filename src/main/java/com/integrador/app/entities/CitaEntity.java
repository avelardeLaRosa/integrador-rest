package com.integrador.app.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "tb_cita")
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private int id;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario",nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_consultorio", nullable = false)
    private ConsultorioEntity consultorio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_diagnostico")
    private DiagnosticoEntity diagnostico;
    /*
    @OneToMany(
            mappedBy = "cita",
            fetch = FetchType.LAZY
    )
    private List<DiagnosticoEntity> diagnostico = new ArrayList<>();

     */





}
