package com.example.practica4;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Persona(
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @NotBlank(message = "Los apellidos no pueden estar vacíos")
        String apellidos,

        @Email(message = "El formato del email es incorrecto")
        @NotBlank(message = "El email es obligatorio")
        String email
) {}
