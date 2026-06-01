package com.example.TP_Integrador_Elian_Rehbani.features.contenedor;

import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto.ContenedorEmbarqueRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto.ContenedorRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto.ContenedorResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto.ContenedorUpdateRequest;

import java.util.List;

public interface IContenedorService {
    List<ContenedorResponse> findAll(String codigoIdentificacion);
    ContenedorResponse findById(Long contenedorId);
    ContenedorResponse save(ContenedorRequest request);
    ContenedorResponse update(ContenedorUpdateRequest contenedor, Long id);
    ContenedorResponse delete(Long id);
    ContenedorResponse embarque(Long id, ContenedorEmbarqueRequest contenedorEmbarque);
}
