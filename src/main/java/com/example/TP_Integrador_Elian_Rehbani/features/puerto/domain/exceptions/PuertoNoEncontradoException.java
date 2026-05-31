package com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.exceptions;

import java.util.NoSuchElementException;

public class PuertoNoEncontradoException extends NoSuchElementException {
    public PuertoNoEncontradoException(String message) {
        super(message);
    }
}
