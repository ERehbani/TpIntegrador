package com.example.TP_Integrador_Elian_Rehbani.features.puerto;

import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("puerto")
@RequiredArgsConstructor
public class PuertoController {
    private final PuertoService service;

    @GetMapping
    public ResponseEntity<List<PuertoResponse>> getAll(@RequestParam(required = false) String nombre){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(nombre));
    }

    @GetMapping("/{puertoId}")
    public ResponseEntity<PuertoResponse> getById(@PathVariable Long puertoId){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(puertoId));
    }

    @PutMapping("/{puertoId}")
    public ResponseEntity<PuertoResponse> update(@RequestBody PuertoUpdateRequest puerto, @PathVariable Long puertoId){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(puerto, puertoId));
    }

    @PostMapping
    public ResponseEntity<PuertoResponse> save(@RequestBody @Valid PuertoRequest puerto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(puerto));
    }

    @DeleteMapping("/{puertoId}")
    public ResponseEntity<PuertoResponse> delete(@PathVariable Long puertoId){
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(puertoId));
    }
}
