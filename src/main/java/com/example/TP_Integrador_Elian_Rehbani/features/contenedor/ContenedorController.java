package com.example.TP_Integrador_Elian_Rehbani.features.contenedor;

import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto.ContenedorEmbarqueRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto.ContenedorRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto.ContenedorResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.dto.ContenedorUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contenedor")
@RequiredArgsConstructor
public class ContenedorController {

    private final ContenedorService service;

    @GetMapping
    public ResponseEntity<List<ContenedorResponse>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{contenedorId}")
    public ResponseEntity<ContenedorResponse> getById(@PathVariable Long contenedorId){
        return ResponseEntity.ok(service.findById(contenedorId));
    }

    @PutMapping("/{contenedorId}")
    public ResponseEntity<ContenedorResponse> update(@RequestBody ContenedorUpdateRequest contenedor, @PathVariable Long contenedorId){
        return ResponseEntity.ok(service.update(contenedor, contenedorId));
    }

    @PatchMapping("/{contenedorId}/embarcar")
    public ResponseEntity<ContenedorResponse> embarque(@PathVariable Long contenedorId, @RequestBody ContenedorEmbarqueRequest contenedor){
        return ResponseEntity.ok(service.embarque(contenedorId, contenedor));
    }

    @PostMapping
    public ResponseEntity<ContenedorResponse> save(@RequestBody @Valid ContenedorRequest contenedor){
        return ResponseEntity.status(HttpStatus.CREATED).body( service.save(contenedor));
    }

    @DeleteMapping("/{contenedorId}")
    public ResponseEntity<ContenedorResponse> delete(@PathVariable Long contenedorId){
        return ResponseEntity.ok(service.delete(contenedorId));
    }
}
