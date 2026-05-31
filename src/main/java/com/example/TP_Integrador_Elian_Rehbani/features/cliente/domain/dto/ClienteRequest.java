package com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequest(

        @NotBlank(message = "El cliente debe contener una razón social")
        String razonSocial,

        @NotBlank(message = "El cliente debe contener un dni")
        String dni,

        @NotNull(message = "Se debe especificar si el cliente es vip")
        Boolean esVip
) {
}
