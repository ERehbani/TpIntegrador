package com.example.TP_Integrador_Elian_Rehbani.common.exceptions;

import com.example.TP_Integrador_Elian_Rehbani.common.dto.ErrorResponseDTO;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.exceptions.BuqueNoActivoException;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.exceptions.BuqueNoEliminadoException;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.exceptions.BuqueNoEncontradoException;
import com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.exceptions.CapacidadExcedidaException;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.exceptions.ClienteNoEliminadoException;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.exceptions.ClienteNoEncontradoException;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.exceptions.ContenedorNoActivoException;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.exceptions.ContenedorNoEncontradoException;
import com.example.TP_Integrador_Elian_Rehbani.features.contenedor.domain.exceptions.DestinoEmbarqueNoDisponible;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.exceptions.PuertoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 NOT FOUND
    @ExceptionHandler(BuqueNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> handleBuqueNoEncontrado(BuqueNoEncontradoException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> handleClienteNoEncontrado(ClienteNoEncontradoException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ContenedorNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> handleContenedorNoEncontrado(ContenedorNoEncontradoException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(PuertoNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> handlePuertoNoEncontrado(PuertoNoEncontradoException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // 409 CONFLICT
    @ExceptionHandler(BuqueNoEliminadoException.class)
    public ResponseEntity<ErrorResponseDTO> handleBuqueNoEliminado(BuqueNoEliminadoException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(ClienteNoEliminadoException.class)
    public ResponseEntity<ErrorResponseDTO> handleClienteNoEliminado(ClienteNoEliminadoException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    // 422 UNPROCESSABLE ENTITY
    @ExceptionHandler(BuqueNoActivoException.class)
    public ResponseEntity<ErrorResponseDTO> handleBuqueNoActivo(BuqueNoActivoException ex) {
        return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(CapacidadExcedidaException.class)
    public ResponseEntity<ErrorResponseDTO> handleCapacidadExcedida(CapacidadExcedidaException ex) {
        return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(ContenedorNoActivoException.class)
    public ResponseEntity<ErrorResponseDTO> handleContenedorNoActivo(ContenedorNoActivoException ex) {
        return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(DestinoEmbarqueNoDisponible.class)
    public ResponseEntity<ErrorResponseDTO> handleDestinoEmbarqueNoDisponible(DestinoEmbarqueNoDisponible ex) {
        return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    // 400 BAD REQUEST — @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return buildResponse(HttpStatus.BAD_REQUEST, errors);
    }

    // 500 INTERNAL SERVER ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleUnexpected(Exception ex) {
        ex.printStackTrace();
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error interno del servidor: " + ex.getMessage());
    }


    private ResponseEntity<ErrorResponseDTO> buildResponse(HttpStatus status, String message) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message
        );
        return new ResponseEntity<>(error, status);
    }
}
