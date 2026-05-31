package com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto;

import jakarta.validation.constraints.NotNull;

public record ContenedorEmbarqueRequest(
        @NotNull(message = "El buque en el que viaja el contenedor es obligatorio")
        Long buqueId
) {
}
