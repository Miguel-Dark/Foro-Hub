package com.aluracursos.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotNull Long idUsuario,
        @NotBlank String mensaje,
        @NotBlank Long idCurso,
        @NotBlank String titulo
) {
}
