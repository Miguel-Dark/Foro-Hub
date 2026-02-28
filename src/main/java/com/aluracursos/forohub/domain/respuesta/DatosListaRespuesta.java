package com.aluracursos.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosListaRespuesta(
        Long id,
        String nombre,
        LocalDateTime fechaCreacion,
        String autor,
        String tituloTopico
) {

    public DatosListaRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getAutor().getNombre(),
                respuesta.getTopico().getTitulo()
        );
    }
}
