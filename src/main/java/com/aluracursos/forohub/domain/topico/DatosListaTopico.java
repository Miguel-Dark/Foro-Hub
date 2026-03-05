package com.aluracursos.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosListaTopico(
        Long id,
        String usuario,
        String titulo,
        String mensaje,
        String nombreCurso,
        LocalDateTime fechaCreacion
) {
    public DatosListaTopico(Topico topico) { //constructor
        this(
                topico.getId(),
                topico.getAutor().getNombre(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso().getNombre(),
                topico.getFechaCreacion()
        );
    }
}
