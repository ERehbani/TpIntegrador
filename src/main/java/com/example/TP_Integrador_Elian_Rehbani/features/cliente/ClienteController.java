package com.example.TP_Integrador_Elian_Rehbani.features.cliente;

import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto.ClienteRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto.ClienteResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto.ClienteUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteResponse> getById(@PathVariable Long clienteId){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(clienteId));
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteResponse> updateClient(@RequestBody ClienteUpdateRequest cliente, @PathVariable Long clienteId){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(cliente, clienteId));
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> saveClient(@RequestBody @Valid ClienteRequest client){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(client));
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<ClienteResponse> deleteClient(@PathVariable Long clienteId){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.delete(clienteId));
    }
}
