package com.integrador.app.entities.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioRequest {

    private int id;
    private String nombre;
    private String apellido;
    private String edad;
    private String sexo;
    private String correo;
    private String telefono;
    private int idPais;
}
