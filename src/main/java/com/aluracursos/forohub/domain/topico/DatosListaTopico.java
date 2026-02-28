package com.aluracursos.forohub.domain.topico;

public record DatosListaTopico(
        Long id,
        String usuario,
        String mensaje,
        String nombreCurso,
        String titulo
) {
    public DatosListaTopico(Topico topico) { //constructor
        this(
                topico.getId(),
                topico.getAutor().getNombre(),
                topico.getMensaje(),
                topico.getCurso().getNombre(),
                topico.getTitulo()
        );
    }
}
