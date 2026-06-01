package com.example.TP_Integrador_Elian_Rehbani.features.buque;

import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto.BuqueRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto.BuqueResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto.BuqueUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buque")
@RequiredArgsConstructor
public class BuqueController {
    private final BuqueService service;

    @GetMapping
    public ResponseEntity<List<BuqueResponse>> getAll(@RequestParam(required = false) String matriculaImo){
        return ResponseEntity.ok(service.findAll(matriculaImo));
    }

    @GetMapping("/{buqueId}")
    public ResponseEntity<BuqueResponse> getById(Long buqueId){
        return ResponseEntity.ok(service.findById(buqueId));
    }

    @PutMapping("/{buqueId}")
    public ResponseEntity<BuqueResponse> update(@RequestBody BuqueUpdateRequest buque, @PathVariable Long buqueId){
        return ResponseEntity.ok(service.update(buque, buqueId));
    }

    @PostMapping
    public ResponseEntity<BuqueResponse> save(@RequestBody @Valid BuqueRequest buque){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(buque));
    }

    @DeleteMapping("/{buqueId}")
    public ResponseEntity<BuqueResponse> delete(@PathVariable Long buqueId){
        return ResponseEntity.ok(service.delete(buqueId));
    }
}
