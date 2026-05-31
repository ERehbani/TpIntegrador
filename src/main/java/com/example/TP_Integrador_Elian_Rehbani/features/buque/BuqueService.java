package com.example.TP_Integrador_Elian_Rehbani.features.buque;

import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto.BuqueRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto.BuqueResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto.BuqueUpdateRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.exceptions.BuqueNoEncontradoException;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.mapper.BuqueMapper;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.ContenedorEntity;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.IContenedorRepository;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.exceptions.BuqueNoEliminadoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuqueService {
    private final IBuqueRepository repository;
    private final IContenedorRepository contenedorRepository;
    private final BuqueMapper mapper;

    public List<BuqueResponse> findAll(){
        return repository.findAll()
                .stream()
                .filter(BuqueEntity::getActivo)
                .map(mapper::toDto)
                .toList();
    }

    private BuqueEntity getById(Long id){
        return  repository.findById(id)
                .filter(BuqueEntity::getActivo)
                .orElseThrow(() -> new BuqueNoEncontradoException("Buque no encontrado"));
    }

    public BuqueResponse findById(Long buqueId){
        return mapper.toDto(getById(buqueId));
    }

    public BuqueResponse save(BuqueRequest buque){
        BuqueEntity buqueEntity = mapper.toEntity(buque);

        return mapper.toDto(repository.save(buqueEntity));
    }

    public BuqueResponse update(BuqueUpdateRequest buque, Long buqueId){
        BuqueEntity buqueEntity = getById(buqueId);

        if (buque.nombre() != null)
            buqueEntity.setNombre(buque.nombre());

        if(buque.matriculaImo() != null)
            buqueEntity.setMatriculaImo(buque.matriculaImo());

        if(buque.capacidadMaximaToneladas() != null)
            buqueEntity.setCapacidadMaximaToneladas(buque.capacidadMaximaToneladas());

        if(buque.estadoOperativo() != null)
            buqueEntity.setEstadoOperativo(buque.estadoOperativo());

        return mapper.toDto(repository.save(buqueEntity));
    }

    public BuqueResponse delete(Long buqueId){
        BuqueEntity buqueEntity = getById(buqueId);

        Long contenedoresDelBuque = contenedorRepository.findAll()
                .stream()
                .filter(ContenedorEntity::getActivo)
                .map(c -> c.getBuque().equals(buqueEntity.getId()))
                .count();

        if(contenedoresDelBuque > 0 || buqueEntity.getEstadoOperativo().equals(EBuqueEstado.EN_RUTA))
            throw new BuqueNoEliminadoException("El buque no puede ser eliminado");

        buqueEntity.setActivo(false);
        return mapper.toDto(repository.save(buqueEntity));
    }
}
