package com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto;

public record ContenedorUpdateRequest (
        String codigoIdentificacion,
        Double pesoToneladas,
        Long clienteId,
        Long puertoId,
        Long buqueId
){
}
