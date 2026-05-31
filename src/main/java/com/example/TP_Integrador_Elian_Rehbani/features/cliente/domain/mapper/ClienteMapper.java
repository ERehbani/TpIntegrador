package com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.mapper;

import com.example.TP_Integrador_Elian_Rehbani.features.cliente.ClienteEntity;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto.ClienteRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto.ClienteResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto.ClienteUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteEntity toEntity(ClienteRequest cliente){
        return ClienteEntity.builder()
                .razonSocial(cliente.razonSocial())
                .dni(cliente.dni())
                .esVip(cliente.esVip())
                .build();
    }

    public ClienteEntity toEntity(ClienteUpdateRequest cliente){
        return ClienteEntity.builder()
                .razonSocial(cliente.razonSocial())
                .esVip(cliente.esVip())
                .build();
    }

    public ClienteResponse toDto(ClienteEntity cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getRazonSocial(),
                cliente.getDni(),
                cliente.getEsVip(),
                cliente.getActivo()
        );
    }
}
