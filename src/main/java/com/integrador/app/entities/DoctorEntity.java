package com.integrador.app.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "especialidad")
    private String especialidad;
    @Column(name = "sexo")
    private String sexo;

    @ManyToOne(fetch = FetchType.LAZY)
    private ConsultorioEntity consultorio;


}
