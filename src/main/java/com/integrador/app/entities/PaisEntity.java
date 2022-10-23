package com.integrador.app.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_pais")
public class PaisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais")
    private int id;
    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    private UsuarioEntity usuarioPais;
}
