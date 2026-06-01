package com.example.TP_Integrador_Elian_Rehbani.features.puerto;

import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoUpdateRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.exceptions.PuertoNoEncontradoException;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.mapper.PuertoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PuertoService implements IPuertoService{
    private final IPuertoRepository repository;
    private final PuertoMapper mapper;

    public List<PuertoResponse> findAll(String nombre){
        return repository.findAll()
                .stream()
                .filter(PuertoEntity::getActivo)
                .filter(p -> nombre == null || nombre.isEmpty() ||
                        p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .map(mapper::toDto)
                .toList();
    }

    public PuertoResponse findById(Long id) {
        return mapper.toDto(
                repository.findById(id)
                        .filter(PuertoEntity::getActivo)
                        .orElseThrow(() -> new PuertoNoEncontradoException("Puerto no encontrado"))
        );
    }

    public PuertoResponse save(PuertoRequest puerto){
        PuertoEntity puertoEntity = mapper.toEntity(puerto);
        return mapper.toDto(repository.save(puertoEntity));
    }

    public PuertoResponse update(PuertoUpdateRequest puerto, Long puertoId){
        PuertoEntity entity = repository.findById(puertoId)
                .orElseThrow(() -> new PuertoNoEncontradoException("Puerto no encontrado"));
     if(puerto.nombre() != null) entity.setNombre(puerto.nombre());
     if(puerto.pais() != null) entity.setPais(puerto.pais());
     return mapper.toDto(repository.save(entity));
    }

    public PuertoResponse delete(Long puertoId){
        PuertoEntity puertoEntity = repository.findById(puertoId)
                .orElseThrow(() -> new PuertoNoEncontradoException("Puerto no encontrado"));
        puertoEntity.setActivo(false);
        return mapper.toDto(repository.save(puertoEntity));
    }
}
