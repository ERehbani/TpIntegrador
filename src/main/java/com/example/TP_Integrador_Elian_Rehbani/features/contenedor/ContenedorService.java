package com.example.TP_Integrador_Elian_Rehbani.features.contenedor;

import com.example.TP_Integrador_Elian_Rehbani.features.buque.BuqueEntity;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.EBuqueEstado;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.IBuqueRepository;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.exceptions.BuqueNoActivoException;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.exceptions.BuqueNoEncontradoException;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.exceptions.CapacidadExcedidaException;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.ClienteEntity;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.IClienteRepository;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.exceptions.ClienteNoEncontradoException;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto.ContenedorEmbarqueRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto.ContenedorRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto.ContenedorResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto.ContenedorUpdateRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.exceptions.ContenedorNoActivoException;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.exceptions.ContenedorNoEncontradoException;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.exceptions.DestinoEmbarqueNoDisponible;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.mapper.ContenedorMapper;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.IPuertoRepository;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.PuertoEntity;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.exceptions.PuertoNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContenedorService implements IContenedorService{

    private final IContenedorRepository repository;
    private final IClienteRepository clienteRepository;
    private final IPuertoRepository puertoRepository;
    private final IBuqueRepository buqueRepository;
    private final ContenedorMapper mapper;

    public List<ContenedorResponse> findAll(String codigoIdentificacion){
        return repository.findAll()
                .stream()
                .filter(ContenedorEntity::getActivo)
                .filter(c -> codigoIdentificacion == null || codigoIdentificacion.isEmpty() ||
                        c.getCodigoIdentificacion().toLowerCase().contains(codigoIdentificacion.toLowerCase()))
                .map(mapper::toDto)
                .toList();
    }

    public ContenedorEntity getById(Long id){
        return repository.findById(id)
                .filter(ContenedorEntity::getActivo)
                .orElseThrow(() -> new ContenedorNoEncontradoException("Contenedor no encontrado"));
    }

    public ContenedorResponse findById(Long contenedorId){
        return mapper.toDto(getById(contenedorId));
    }

    public ContenedorResponse save(ContenedorRequest request){
        ClienteEntity cliente =
                clienteRepository.findById(request.clienteId())
                        .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado"));
        PuertoEntity puerto = puertoRepository.findById(request.puertoId())
                .orElseThrow(() -> new PuertoNoEncontradoException("Puerto no encontrado"));
        BuqueEntity buque = buqueRepository.findById(request.buqueId())
                .orElseThrow(() -> new BuqueNoEncontradoException("Buque no encontrado"));

        ContenedorEntity entity = mapper.toEntity(request, cliente, puerto, buque);

        return mapper.toDto(repository.save(entity));
    }

    public ContenedorResponse update(ContenedorUpdateRequest contenedor, Long id){
        ContenedorEntity entity = getById(id);

        if(contenedor.codigoIdentificacion() != null)
            entity.setCodigoIdentificacion(contenedor.codigoIdentificacion());

        if(contenedor.pesoToneladas() != null)
            entity.setPesoToneladas(contenedor.pesoToneladas());

        if(contenedor.clienteId() != null){
            ClienteEntity cliente =
                    clienteRepository.findById(contenedor.clienteId())
                            .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado"));
            entity.setCliente(cliente);
        }

        if (contenedor.puertoId() != null){
            PuertoEntity puerto = puertoRepository.findById(contenedor.puertoId())
                    .orElseThrow(() -> new PuertoNoEncontradoException("Puerto no encontrado"));

            entity.setPuerto(puerto);
        }

        if(contenedor.buqueId() != null){
            BuqueEntity buque = buqueRepository.findById(contenedor.buqueId())
                    .orElseThrow(() -> new BuqueNoEncontradoException("Buque no encontrado"));
            entity.setBuque(buque);
        }

        return mapper.toDto(repository.save(entity));
    }

    public ContenedorResponse delete(Long id){
        ContenedorEntity entity = getById(id);
        entity.setActivo(false);
        return mapper.toDto(repository.save(entity));
    }


    public ContenedorResponse embarque(Long id, ContenedorEmbarqueRequest contenedorEmbarque){
        ContenedorEntity contenedor = getById(id);


        List<ContenedorEntity> contenedoresDeBuque = repository.findByBuqueIdAndActivoTrue(contenedorEmbarque.buqueId());
        Double pesoAcumulado = contenedoresDeBuque.stream()
                .mapToDouble(ContenedorEntity::getPesoToneladas)
                .sum();



        BuqueEntity buque = buqueRepository.findById(contenedorEmbarque.buqueId())
                .orElseThrow(() -> new BuqueNoEncontradoException("El buque para el embarque no fue encontrado"));

        String puertoContenedor = contenedor.getPuerto().getNombre();
        long puertoBuque = buque.getPuertos()
                .stream()
                .filter(b -> b.getNombre().equalsIgnoreCase(puertoContenedor))
                .count();
        boolean pesoExcedido = (pesoAcumulado + contenedor.getPesoToneladas()) > buque.getCapacidadMaximaToneladas();

        if (!buque.getActivo() || !buque.getEstadoOperativo().equals(EBuqueEstado.EN_PUERTO))
            throw new BuqueNoActivoException("El buque para el embarque no esta disponible");
        if (!contenedor.getActivo())
            throw new ContenedorNoActivoException("El contenedor para el embarque no esta activo");
        if(puertoBuque == 0)
            throw new DestinoEmbarqueNoDisponible("El destino del embarque no se encuentra en los destinos del buque asociado");
        if(pesoExcedido) {

            if(!contenedor.getCliente().getEsVip())
                throw new CapacidadExcedidaException("Capacidad del buque excedida");

            Double pesoNecesario = (pesoAcumulado + contenedor.getPesoToneladas()) - buque.getCapacidadMaximaToneladas();

            ContenedorEntity contenedorNoVip = contenedoresDeBuque.stream()
                    .filter(c -> !c.getCliente().getEsVip())
                    .filter(c -> c.getPesoToneladas() >= pesoNecesario)
                    .findFirst()
                    .orElseThrow(() -> new CapacidadExcedidaException("Capacidad del buque excedida, no hay contenedores para desalojar"));

            contenedorNoVip.setBuque(null);
            repository.save(contenedorNoVip);

            if (buque.getPesoAcumulado() != null) {
                buque.setPesoAcumulado(buque.getPesoAcumulado() - contenedorNoVip.getPesoToneladas());
            }
            buqueRepository.save(buque);
        }

        contenedor.setBuque(buque);

        if (buque.getPesoAcumulado() == null) {
            buque.setPesoAcumulado(contenedor.getPesoToneladas());
        } else {
            buque.setPesoAcumulado(buque.getPesoAcumulado() + contenedor.getPesoToneladas());
        }
        buqueRepository.save(buque);

        return mapper.toDto(repository.save(contenedor));
    }
}