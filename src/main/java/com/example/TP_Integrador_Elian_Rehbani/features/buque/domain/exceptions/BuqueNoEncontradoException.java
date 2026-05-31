package com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.exceptions;

import java.util.NoSuchElementException;

public class BuqueNoEncontradoException extends NoSuchElementException {
    public BuqueNoEncontradoException(String message) {
        super(message);
    }
}
