package com.aluracursos.forohub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(
        @NotNull Long id,
        String mensaje,
        String titulo,
        Long idUsuario,
        Long idCurso
) {
}
