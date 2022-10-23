package com.integrador.app.excepciones;

import lombok.Getter;


@Getter
public enum Messages {

    OK("Proceso exitoso.", 200),
    CREATED("Creacion exitosa", 201),
    UPDATED("Actualizacion exitosa", 200),
    DELETED("Eliminacion exitosa", 200),
    BADREQUEST("Error en su peticion", 400),
    NOT_FOUND("Recurso no encontrado", 404),
    INTERNAL_ERROR("Error en el servicio", 500),
    CONFLICT("Conflicto en su peticion", 409);


    private String message;
    private int code;

    private Messages(String message,Integer code){
        this.message = message;
        this.code = code;
    }




}
