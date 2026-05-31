package com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto;

import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto.BuqueResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto.ClienteResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoResponse;

public record ContenedorResponse(
        Long id,
        String codigoIdentificacion,
        Double pesoToneladas,
        Boolean activo,
        ClienteResponse cliente,
        PuertoResponse puerto,
        BuqueResponse buque
) {
}
