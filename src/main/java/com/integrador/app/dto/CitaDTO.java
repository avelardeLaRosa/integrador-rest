package com.integrador.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.integrador.app.entities.ConsultorioEntity;
import com.integrador.app.entities.DiagnosticoEntity;
import com.integrador.app.entities.UsuarioEntity;
import com.integrador.app.util.ConstantesServicio;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CitaDTO {

    private int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstantesServicio.DATE_FORMAT)
    private LocalDateTime fechaCreacion;
    private UsuarioEntity usuario;
    private ConsultorioEntity consultorio;
    private List<DiagnosticoDTO> diagnosticoDTOList = new ArrayList<>();


}
