package com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.mapper;

import com.example.TP_Integrador_Elian_Rehbani.features.puerto.PuertoEntity;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoResponse;
import org.springframework.stereotype.Component;

@Component
public class PuertoMapper {

    public PuertoEntity toEntity(PuertoRequest request){
        return PuertoEntity.builder()
                .nombre(request.nombre())
                .pais(request.pais())
                .activo(true)
                .build();
    }

    public PuertoResponse toDto(PuertoEntity entity){
        return new PuertoResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getPais(),
                entity.getActivo()
        );
    }
}
