package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topico.StatusTopico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estadisticas")
@SecurityRequirement(name = "bearer-key")
public class EstadisticaController {

    @Autowired
    private TopicoRepository topicoRepository;

    @GetMapping
    public ResponseEntity obtenerEstadisticas() {
        var total = topicoRepository.count();
        var resueltos = topicoRepository.countByStatus(StatusTopico.SOLUCIONADO);
        var sinRespuesta = topicoRepository.countBySinRespuestas();

        double porcentajeResueltos = (total > 0) ? ((double) resueltos / total) * 100 : 0;

        return ResponseEntity.ok(java.util.Map.of(
                "totalTopicos", total,
                "topicosResueltos", resueltos,
                "topicosSinRespuesta", sinRespuesta,
                "porcentajeExito", String.format("%.2f%%", porcentajeResueltos),
                "marca", "Miguel-Dark Stats"
        ));
    }
}
