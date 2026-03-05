package com.aluracursos.forohub.infra.errores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(ValidacionDeIntegridadException.class)
    public ResponseEntity manejarErrorDeDuplicados(ValidacionDeIntegridadException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
