package com.integrador.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.integrador.app.entities.UsuarioEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaisDTO {

    private int id;
    private String descripcion;
    private UsuarioDTO usuarioDTO;
}
