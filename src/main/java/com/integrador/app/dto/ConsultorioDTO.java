package com.integrador.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultorioDTO {


    private int id;
    private String descripcion;
    private String contacto;
    private String correo;
    private String direccion;
    //private List<CitaDTO> citasConsultDTOList = new ArrayList<>();
    private List<DoctorDTO> doctoresDTO = new ArrayList<>();

}
