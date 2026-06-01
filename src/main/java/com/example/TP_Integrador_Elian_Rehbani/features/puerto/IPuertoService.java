package com.example.TP_Integrador_Elian_Rehbani.features.puerto;

import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoUpdateRequest;

import java.util.List;

public interface IPuertoService {
    List<PuertoResponse> findAll(String nombre);
    PuertoResponse findById(Long id);
    PuertoResponse save(PuertoRequest puerto);
    PuertoResponse update(PuertoUpdateRequest puerto, Long puertoId);
    PuertoResponse delete(Long puertoId);
}
