package com.integrador.app.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_diagnostico")
public class DiagnosticoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diagnostico")
    private int id;
    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cita")
    private CitaEntity cita;
}
