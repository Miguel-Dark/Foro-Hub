package com.aluracursos.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        Long idTopico,
        String nombreTopico,
        LocalDateTime fechaCreacion,
        Long idAutor,
        String nombreAutor,
        Boolean solucion
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getId(),
                respuesta.getTopico().getTitulo(),
                respuesta.getFechaCreacion(),
                respuesta.getAutor().getId(),
                respuesta.getAutor().getNombre(),
                respuesta.getSolucion()
        );
    }
}
