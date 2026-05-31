package com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record PuertoRequest(

        @NotBlank(message = "Se debe especificar un nombre para el puerto")
        String nombre,
        @NotBlank(message = "Se debe especificar un pais para el puerto")
        String pais
) {
}
