package com.integrador.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.integrador.app.entities.ConsultorioEntity;
import com.integrador.app.entities.DiagnosticoEntity;
import com.integrador.app.entities.DoctorEntity;
import com.integrador.app.entities.UsuarioEntity;
import com.integrador.app.util.ConstantesServicio;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CitaDTO {

   private int id;

   private String code;
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstantesServicio.DATE_TIME_FORMAT)
    private String fechaCreacion;

    private int usuario;
    private int consultorio;
    private int doctor;
    private int diagnostico;

}
