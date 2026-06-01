package com.example.TP_Integrador_Elian_Rehbani.features.cliente;

import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto.ClienteRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto.ClienteResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto.ClienteUpdateRequest;

import java.util.List;

public interface IClienteService {
    List<ClienteResponse> findAll(String clienteDni);
    ClienteResponse findById(Long id);
    ClienteResponse save(ClienteRequest cliente);
    ClienteResponse update(ClienteUpdateRequest cliente, Long id);
    ClienteResponse delete(Long id);
}
