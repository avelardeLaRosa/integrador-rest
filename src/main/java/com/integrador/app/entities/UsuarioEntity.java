package com.integrador.app.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tb_usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "edad")
    private String edad;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "correo")
    private String correo;
    @Column(name = "telefono")
    private String telefono;

    @OneToMany(
            mappedBy = "usuario",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,orphanRemoval = true)
    private List<CitaEntity> citas = new ArrayList<>();

    @OneToMany(
            mappedBy = "usuarioPais",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,orphanRemoval = true
    )
    private List<PaisEntity> paises = new ArrayList<>();

}
