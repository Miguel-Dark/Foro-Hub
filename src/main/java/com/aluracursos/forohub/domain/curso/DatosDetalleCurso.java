package com.aluracursos.forohub.domain.curso;

public record DatosDetalleCurso(
        Long id,
        String nombre,
        CategoriaCurso categoria
) {
    public DatosDetalleCurso(Curso curso) {
        this(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria());
    }
}
