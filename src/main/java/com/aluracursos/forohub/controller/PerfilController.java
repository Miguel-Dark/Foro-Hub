package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.perfil.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/perfiles")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    @Autowired
    private PerfilRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroPerfil datos,
                                    UriComponentsBuilder uriComponentsBuilder) {
        var perfil = repository.save(new Perfil(datos.nombre()));
        var url = uriComponentsBuilder.path("/perfiles/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosDetallePerfil(perfil));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaPerfil>> listar(@PageableDefault(size=10,
            sort={"nombre"}, direction = Sort.Direction.ASC) Pageable paginacion) {
        var page = repository.findAllByActivoTrue(paginacion).map(DatosListaPerfil::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var optionalPerfil = repository.findById(id);

        if (optionalPerfil.isPresent()) {
            var perfil = optionalPerfil.get();
            return ResponseEntity.ok(new DatosDetallePerfil(perfil));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarPerfil datos) {
        var optionalPerfil = repository.findById(id);
        if (optionalPerfil.isPresent()) {
            var perfil = optionalPerfil.get();
            perfil.actualizarInformacion(datos);

            return ResponseEntity.ok(new DatosDetallePerfil(perfil));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        var optionalPerfil = repository.findById(id);
        if (optionalPerfil.isPresent()) {
            var perfil = optionalPerfil.get();
            perfil.eliminarLogico();
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
