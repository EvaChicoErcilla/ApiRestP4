package com.example.practica4;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ExcepcionPersonaInvalida extends RuntimeException {
    private final List<FieldError> errores;

    public ExcepcionPersonaInvalida(BindingResult result) {

        this.errores = result.getFieldErrors();
    }

    public List<FieldError> getErrores() {
        return errores;
    }
}




