package com.example.TP_Integrador_Elian_Rehbani.features.cliente;

import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto.ClienteRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto.ClienteResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto.ClienteUpdateRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.exceptions.ClienteNoEliminadoException;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.exceptions.ClienteNoEncontradoException;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.mapper.ClienteMapper;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.IContenedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final IClienteRepository repository;
    private final IContenedorRepository contenedorRepository;
    private final ClienteMapper mapper;

    public List<ClienteResponse> findAll(){
        return repository.findAll()
                .stream()
                .filter(ClienteEntity::getActivo)
                .map(mapper::toDto)
                .toList();
    }

    private ClienteEntity getById(Long id){
        return repository.findById(id)
                .filter(ClienteEntity::getActivo)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado"));
    }

    public ClienteResponse findById(Long id){
        return mapper.toDto(getById(id));
    }

    public ClienteResponse save(ClienteRequest cliente){
        ClienteEntity clienteEntity = mapper.toEntity(cliente);
        clienteEntity.setActivo(true);
        return mapper.toDto(repository.save(clienteEntity));
    }

    public ClienteResponse update(ClienteUpdateRequest cliente, Long id){
        ClienteEntity clienteEntity = getById(id);
       if (cliente.razonSocial() != null) clienteEntity.setRazonSocial(cliente.razonSocial());
       if (cliente.esVip() != null) clienteEntity.setEsVip(cliente.esVip());
       return mapper.toDto(repository.save(clienteEntity));

    }

    public ClienteResponse delete(Long id){
        ClienteEntity clienteEntity = getById(id);

        Long contenedoresDeCliente = contenedorRepository.findAll()
                .stream()
                .filter(c -> Objects.equals(c.getCliente().getId(), clienteEntity.getId()))
                .count();

        if(contenedoresDeCliente > 0) throw new ClienteNoEliminadoException("El cliente no puede eliminarse");

        clienteEntity.setActivo(false);
        return mapper.toDto(repository.save(clienteEntity));
    }
}
