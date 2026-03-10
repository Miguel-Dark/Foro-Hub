package com.aluracursos.forohub.domain.usuario;

public record DatosPerfilUsuario(
        Long id,
        String nombre,
        String email,
        String rol,
        long topicosAbiertos,
        long medallasSolucion,
        String rango
) {
}
