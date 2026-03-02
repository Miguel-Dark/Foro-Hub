package com.aluracursos.forohub.domain.usuario;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DatosActualizarUsuario(
        @NotNull Long id,
        String nombre,
        String email,
        List<Long> idPerfiles
) {
}
