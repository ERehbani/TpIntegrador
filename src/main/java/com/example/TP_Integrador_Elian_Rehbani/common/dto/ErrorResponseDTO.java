package com.example.TP_Integrador_Elian_Rehbani.common.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        LocalDateTime timestamp,
        int status,
        String error,
        String message
) {}
