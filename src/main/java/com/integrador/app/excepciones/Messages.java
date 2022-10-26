package com.integrador.app.excepciones;

import lombok.Getter;


@Getter
public enum Messages {

    OK("Proceso exitoso.", 200),
    CREATED("Creacion exitosa", 201),
    UPDATED("Actualizacion exitosa", 200),
    DELETED("Eliminacion exitosa", 200),
    BADREQUEST("Error en su peticion", 400),

    INTERNAL_ERROR("Error en el servicio", 500),
    USER_NOT_FOUND("Usuario no encontrado", 500),
    PAIS_NOT_FOUND("Pais no encontrado", 500),
    EMAIL_EXISTS("Correo ya ingresado", 500),
    CONFLICT("Conflicto en su peticion", 409);


    private String message;
    private int code;

    private Messages(String message,Integer code){
        this.message = message;
        this.code = code;
    }




}
