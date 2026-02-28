package com.aluracursos.forohub.domain.curso;

public record DatosListaCurso(
        Long id,
        String nombre,
        CategoriaCurso categoria
) {
    public DatosListaCurso(Curso curso) {
        this
                (curso.getId(),
                 curso.getNombre(),
                 curso.getCategoria());
    }
}
