package com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.mapper;

import com.example.TP_Integrador_Elian_Rehbani.features.buque.BuqueEntity;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.EBuqueEstado;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto.BuqueRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto.BuqueResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuqueMapper {

    public BuqueEntity toEntity(BuqueRequest buque){
        return BuqueEntity.builder()
                .nombre(buque.nombre())
                .matriculaImo(buque.matriculaImo())
                .capacidadMaximaToneladas(buque.capacidadMaximaToneladas())
                .estadoOperativo(buque.estadoOperativo())
                .activo(true)
                .puertos(new ArrayList<>())
                .build();
    }

    public BuqueResponse toDto(BuqueEntity entity){
        List<PuertoResponse> puertos = entity.getPuertos()
                .stream()
                .map(p -> new PuertoResponse(p.getId(), p.getNombre(), p.getPais(), p.getActivo()))
                .toList();

        return new BuqueResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getMatriculaImo(),
                entity.getCapacidadMaximaToneladas(),
                entity.getEstadoOperativo(),
                entity.getActivo(),
                puertos
        );
    }
}
