package com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClienteRequest(

        @NotBlank(message = "El cliente debe contener una razón social")
        String razonSocial,

        @NotBlank(message = "El cliente debe contener un dni")
        @Pattern(
                regexp = "^\\d{8}$",
                message = "El DNI debe contener exactamente 8 dígitos numéricos"
        )
        String dni,

        @NotNull(message = "Se debe especificar si el cliente es vip")
        Boolean esVip
) {
}
