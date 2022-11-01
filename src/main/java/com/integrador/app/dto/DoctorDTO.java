package com.integrador.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.integrador.app.entities.ConsultorioEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDTO {


    private int id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String sexo;
    private String consultorioDTO;
}
