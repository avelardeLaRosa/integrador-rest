package com.integrador.app.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tb_consultorio")
public class ConsultorioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consultorio")
    private int id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "contacto")
    private String contacto;
    @Column(name = "correo")
    private String correo;

    @Column(name = "direccion")
    private String direccion;

    @OneToMany(
            mappedBy = "consultorio",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<CitaEntity> citasConsultorio = new ArrayList<>();

    @OneToMany(
            mappedBy = "consultorio",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<DoctorEntity> doctores = new ArrayList<>();

}
