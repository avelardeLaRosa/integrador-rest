package com.integrador.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.integrador.app.entities.response.CitaResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {


    private int id;
    private String nombre;
    private String apellido;
    private String edad;
    private String sexo;
    private String correo;
    private String telefono;
    private String pais;
    private List<CitaResponse> cita = new ArrayList<>();

}
