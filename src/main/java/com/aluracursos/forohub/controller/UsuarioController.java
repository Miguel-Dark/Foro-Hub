package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.perfil.Perfil;
import com.aluracursos.forohub.domain.perfil.PerfilRepository;
import com.aluracursos.forohub.domain.topico.DatosActualizacionTopico;
import com.aluracursos.forohub.domain.topico.DatosDetalleTopico;
import com.aluracursos.forohub.domain.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroUsuario datos,
                                    UriComponentsBuilder uriComponentsBuilder){
        var perfilUsuario = perfilRepository.findByNombre("USUARIO");
        var usuario = repository.save(new Usuario(datos, List.of(perfilUsuario)));

        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaUsuario>> listar(@PageableDefault(size=10,
            sort={"nombre"}) Pageable paginacion) {
        var page = repository.findAll(paginacion).map(DatosListaUsuario::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var optionalUsuario = repository.findById(id);

        if (optionalUsuario.isPresent()) {
            var usuario = optionalUsuario.get();
            return ResponseEntity.ok(new DatosDetalleUsuario(usuario));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarUsuario datos) {
        var optionalUsuario = repository.findById(id);
        if (optionalUsuario.isPresent()) {
            var usuario = optionalUsuario.get();

            List<Perfil> nuevosPerfiles = null;
            if (datos.idPerfiles() != null) {
                nuevosPerfiles = perfilRepository.findAllById(datos.idPerfiles());
            }

            usuario.actualizarInformacion(datos, nuevosPerfiles);
            return ResponseEntity.ok(new DatosDetalleUsuario(usuario));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        var usuario = repository.findById(id);

        if (usuario.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
