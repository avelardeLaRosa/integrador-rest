package com.integrador.app.excepciones;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Getter
public class MyException extends Exception{

    private static final long serialVersionUID = 1L;
    private ArrayList<String> messages = new ArrayList<>();
    private Integer code;
    private HttpStatus status;

    public MyException(ArrayList<String> messages, Integer code){
        super();
        this.messages = messages;
        this.code = code;
    }

    public MyException(String messages, Integer code){
        super();
        this.messages.add(messages);
        this.code = code;
    }

    public MyException(ArrayList<String> messages, HttpStatus status){
        super();
        this.messages = messages;
        this.status = status;
    }

    public MyException(String messages, HttpStatus status){
        super();
        this.messages.add(messages);
        this.status = status;
    }




}
