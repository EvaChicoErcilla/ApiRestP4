package com.example.practica4;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@ControllerAdvice
public class ManejadorErroresGlobales {
    @ResponseBody
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity errorLanzado(ResponseStatusException ex) {
        return new ResponseEntity<>(ex.getStatusCode());
    }
}

