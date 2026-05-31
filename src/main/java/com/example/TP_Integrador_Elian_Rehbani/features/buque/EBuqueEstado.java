package com.example.TP_Integrador_Elian_Rehbani.features.buque;

public enum EBuqueEstado {
    EN_PUERTO("En puerto"),
    EN_RUTA("En ruta"),
    MANTENIMIENTO("Mantenimiento");

    String message;

    EBuqueEstado(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
