package com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.exceptions;

import java.util.NoSuchElementException;

public class ClienteNoEncontradoException extends NoSuchElementException {
    public ClienteNoEncontradoException(String message) {
        super(message);
    }
}
