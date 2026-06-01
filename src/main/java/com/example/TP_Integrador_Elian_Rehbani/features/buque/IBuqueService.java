package com.example.TP_Integrador_Elian_Rehbani.features.buque;

import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto.BuqueRequest;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto.BuqueResponse;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto.BuqueUpdateRequest;

import java.util.List;

public interface IBuqueService {
    List<BuqueResponse> findAll(String matriculaImo);
    BuqueResponse findById(Long buqueId);
    BuqueResponse save(BuqueRequest buque);
    BuqueResponse update(BuqueUpdateRequest buque, Long buqueId);
    BuqueResponse delete(Long buqueId);
}
