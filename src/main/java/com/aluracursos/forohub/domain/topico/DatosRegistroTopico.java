package com.aluracursos.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotNull Long usuario,
        @NotBlank String mensaje,
        @NotBlank String nombreCurso,
        @NotBlank String titulo
) {
}
