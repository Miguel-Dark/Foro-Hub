package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.topico.*;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
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
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos,
                                    UriComponentsBuilder uriComponentsBuilder){
        var usuario = usuarioRepository.getReferenceById(datos.idUsuario());
        var curso = cursoRepository.getReferenceById(datos.idCurso());
        var topico = repository.save(new Topico(datos, usuario, curso));

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listar(@PageableDefault(
            size=10, sort={"fechaCreacion"}, direction = Sort.Direction.ASC) Pageable paginacion) {
        var page = repository.findAll(paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var optionalTopico = repository.findById(id);

        if (optionalTopico.isPresent()) {
            var topico = optionalTopico.get();
            return ResponseEntity.ok(new DatosDetalleTopico(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos) {
        var optionalTopico = repository.findById(id);
        if (optionalTopico.isPresent()) {
            var topico = optionalTopico.get();

            Usuario nuevoAutor = null;
            if (datos.idUsuario() != null) {
                nuevoAutor = usuarioRepository.getReferenceById(datos.idUsuario());
            }

            Curso nuevoCurso = null;
            if (datos.idCurso() != null) {
                nuevoCurso = cursoRepository.getReferenceById(datos.idCurso());
            }

            topico.actualizarInformacion(datos, nuevoAutor, nuevoCurso);
            return ResponseEntity.ok(new DatosDetalleTopico(topico));
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        var topico = repository.findById(id);

        if (topico.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
