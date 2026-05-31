package com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.exceptions;

import java.util.NoSuchElementException;

public class ContenedorNoEncontradoException extends NoSuchElementException {
    public ContenedorNoEncontradoException(String message) {
        super(message);
    }
}
