package com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto;

import com.example.TP_Integrador_Elian_Rehbani.features.buque.EBuqueEstado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record BuqueRequest(
        @NotBlank(message = "El buque debe tener un nombre")
        String nombre,
        @NotBlank(message = "El buque debe tener una matricula IMO")
        String matriculaImo,
        @NotNull(message = "Se debe especificar una capacidad maxima (Tn) para el buque")
        @PositiveOrZero(message = "La capacidad maxima (Tn) debe ser un numero positivo o cero")
        Double capacidadMaximaToneladas,

        @NotBlank(message = "Se debe especificar un estado para el buque")
        EBuqueEstado estadoOperativo
) {
}
