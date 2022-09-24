package com.ventas.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception{

    private final HttpStatus statusCode;

    public NotFoundException (String message, HttpStatus statusCode){

        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode(){

        return this.statusCode;
    }
}
