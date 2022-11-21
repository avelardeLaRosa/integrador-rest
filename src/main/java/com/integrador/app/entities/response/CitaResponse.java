package com.integrador.app.entities.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.integrador.app.util.ConstantesServicio;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CitaResponse {
    private int id;
    private String code;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstantesServicio.DATE_TIME_FORMAT)
    private String fechaCreacion;

    private String usuario;
    private String consultorio;
    private String doctor;
    private String diagnostico;
}
