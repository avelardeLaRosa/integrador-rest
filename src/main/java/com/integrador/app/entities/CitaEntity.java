package com.integrador.app.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.integrador.app.util.ConstantesServicio;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "tb_cita")
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private int id;

    @Column(name = "cita_code")
    private String code;

    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    @Column(name = "fecha_cita")
    private String fecha;



    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario",nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_consultorio", nullable = false)
    private ConsultorioEntity consultorio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doctor")
    private DoctorEntity doctor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_diagnostico")
    private DiagnosticoEntity diagnostico;


}
