package com.integrador.app.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class UsuarioRequest {
    private int id;
    private String nombre;
    private String apellido;
    private String edad;
    private String sexo;
    private String correo;
    private String telefono;
}
