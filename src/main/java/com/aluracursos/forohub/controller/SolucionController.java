package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.respuesta.RespuestaRepository;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/soluciones")
@SecurityRequirement(name = "bearer-key")
public class SolucionController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    @PutMapping("/{idRespuesta}")
    public ResponseEntity marcarComoGanadora(@PathVariable Long idRespuesta) {
        // 1. Buscamos la respuesta
        var respuesta = respuestaRepository.findById(idRespuesta)
                .orElseThrow(() -> new EntityNotFoundException());

        respuesta.marcarComoSolucion();

        return ResponseEntity.ok(java.util.Map.of(
                "mensaje", "¡Felicidades! Has marcado esta respuesta como la solución.",
                "tópico", respuesta.getTopico().getTitulo(),
                "autorRespuesta", respuesta.getAutor().getNombre(),
                "marca", "Miguel-Dark Solutions ✅"
        ));
    }
}
