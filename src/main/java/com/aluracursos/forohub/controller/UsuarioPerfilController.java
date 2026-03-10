package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.usuario.DatosPerfilUsuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/perfiles/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioPerfilController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/{id}")
    public ResponseEntity obtenerPerfil(@PathVariable Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        var topicos = usuarioRepository.countTopicosPorUsuario(id);
        var soluciones = usuarioRepository.countSolucionesPorUsuario(id);

        String rango = (soluciones >= 5) ? "Instructor Senior" :
                (soluciones >= 1) ? "Colaborador Destacado" : "Estudiante Activo";

        String nombrePerfil = usuario.getPerfiles().isEmpty() ? "SIN_PERFIL" : usuario.getPerfiles().get(0).getNombre();

        var datosPerfil = new DatosPerfilUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                nombrePerfil,
                topicos,
                soluciones,
                rango
        );

        return ResponseEntity.ok(datosPerfil);
    }

    @GetMapping("/ranking")
    public ResponseEntity obtenerRanking() {
        var ranking = usuarioRepository.findAll().stream()
                .map(u -> {
                    var soluciones = usuarioRepository.countSolucionesPorUsuario(u.getId());
                    return Map.of(
                            "nombre", u.getNombre(),
                            "medallas", soluciones,
                            "marca", "Miguel-Dark"
                    );
                })
                .sorted((m1, m2) -> ((Long) m2.get("medallas")).compareTo((Long) m1.get("medallas")))
                .limit(3)
                .toList();

        return ResponseEntity.ok(ranking);
    }
}
