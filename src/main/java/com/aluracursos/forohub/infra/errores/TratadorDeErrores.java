package com.aluracursos.forohub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(ValidacionDeIntegridadException.class)
    public ResponseEntity manejarErrorDeDuplicados(ValidacionDeIntegridadException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(java.util.Map.of(
                        "error", "Recurso no encontrado",
                        "mensaje", "El ID solicitado no existe en la base de datos."
                ));
    }
}
