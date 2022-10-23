package com.integrador.app.entities.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Data
public class OutPutEntity<T> {

    private HttpStatus code;
    private ArrayList<String> messages = new ArrayList<>();
    private Boolean exitoso = true;
    private T data;

    public OutPutEntity<T> success(Integer code, String message, T data){

        this.code = this.code(code);
        this.messages.add(message);
        this.data = data;
        return this;
    }

    private HttpStatus code(Integer code){
        HttpStatus status = null;
        switch (code){
            case 200:
                status = HttpStatus.OK;
                break;
            case 201:
                status = HttpStatus.CREATED;
                break;
            case 404:
                status = HttpStatus.NOT_FOUND;
                break;
            case 400:
                status = HttpStatus.BAD_REQUEST;
                break;
            case 500:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
        }
        return status;
    }

}
