package com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContenedorRequest(
        @NotBlank(message = "El contenedor debe tener un codigo de identificacion")
        String codigoIdentificacion,

        @NotNull(message = "Se debe especificar el peso (Tn) que soporta el contenedor")
        Double pesoToneladas,

        @NotNull(message = "Se debe especificar el cliente al que pertenece el contenedor")
        Long clienteId,

        @NotNull(message = "Se debe especificar el puerto al que se dirige el contenedor")
        Long puertoId,

        @NotNull(message = "Se debe especificar el buque en el que viaja el contenedor")
        Long buqueId
) {
}
