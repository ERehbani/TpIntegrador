package com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.mapper;

import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto.BuqueResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.ClienteEntity;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto.ClienteResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.ContenedorEntity;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto.ContenedorRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto.ContenedorResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.PuertoEntity;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoResponse;
import org.springframework.stereotype.Component;

@Component
public class ContenedorMapper {

    public ContenedorEntity toEntity(ContenedorRequest contenedor, ClienteEntity cliente, PuertoEntity puerto){
        return ContenedorEntity.builder()
                .codigoIdentificacion(contenedor.codigoIdentificacion())
                .pesoToneladas(contenedor.pesoToneladas())
                .activo(true)
                .cliente(cliente)
                .puerto(puerto)
                .buque(null)
                .build();
    }

    public ContenedorResponse toDto(ContenedorEntity entity){
        ClienteResponse cliente =
                new ClienteResponse(entity.getCliente().getId(), entity.getCliente().getRazonSocial(), entity.getCliente().getDni(), entity.getCliente().getEsVip(), entity.getCliente().getActivo());
        PuertoResponse puerto =
                new PuertoResponse(entity.getPuerto().getId(), entity.getPuerto().getNombre(), entity.getPuerto().getPais(), entity.getPuerto().getActivo());

        BuqueResponse buque =
                new BuqueResponse(entity.getPuerto().getId(), entity.getBuque().getNombre(), entity.getBuque().getMatriculaImo(), entity.getBuque().getCapacidadMaximaToneladas(), entity.getBuque().getEstadoOperativo(), entity.getBuque().getActivo(), null);

        return new ContenedorResponse(
                entity.getId(),
                entity.getCodigoIdentificacion(),
                entity.getPesoToneladas(),
                entity.getActivo(),
                cliente,
                puerto,
                buque
        );
    }
}
