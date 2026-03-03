package com.aluracursos.forohub.infra.errores;

public class ValidacionDeIntegridadException extends RuntimeException{
    public ValidacionDeIntegridadException(String mensaje) {
        super(mensaje);
    }
}
