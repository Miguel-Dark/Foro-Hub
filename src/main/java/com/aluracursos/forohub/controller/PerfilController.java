package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.perfil.Perfil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private PerfilRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetallePerfil> registrar(@RequestBody @Valid DatosRegistroPerfil datos,
                                                        UriComponentsBuilder uriComponentsBuilder) {
        Perfil perfil = repository.save(new Perfil(datos));
        DatosDetallePerfil datosDetallePerfil = new DatosDetallePerfil(perfil);
        var url = uriComponentsBuilder.path("/perfiles/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(url).body(datosDetallePerfil);
    }
}
