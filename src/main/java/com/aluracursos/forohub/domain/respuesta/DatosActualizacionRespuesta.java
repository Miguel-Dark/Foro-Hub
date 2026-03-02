package com.aluracursos.forohub.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionRespuesta(
        @NotNull Long id,
        String mensaje,
        Long idTopico,
        Long idUsuario,
        Boolean solucion
) {
}
