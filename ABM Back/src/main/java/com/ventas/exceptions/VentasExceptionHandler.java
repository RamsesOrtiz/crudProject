package com.ventas.exceptions;

import com.ventas.bean.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VentasExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e){
        return ResponseEntity.status(e.getStatusCode()).body(new ErrorResponse("Error", e.getMessage()));
    }
}
