package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.respuesta.*;
import com.aluracursos.forohub.domain.topico.DatosDetalleTopico;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
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
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroRespuesta datos,
                                    UriComponentsBuilder uriComponentsBuilder){
        var autor = usuarioRepository.getReferenceById(datos.idUsuario());
        var topico = topicoRepository.getReferenceById(datos.idTopico());
        var respuesta = new Respuesta(datos.mensaje(), autor, topico);
        respuesta = repository.save(respuesta);
        var uri = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleRespuesta(respuesta));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaRespuesta>> listar(@PageableDefault(
            size=10, sort={"fechaCreacion"}, direction = Sort.Direction.ASC) Pageable paginacion) {
            var page = repository.findAll(paginacion).map(DatosListaRespuesta::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        return repository.findById(id)
                .map(respuesta -> ResponseEntity.ok(new DatosDetalleRespuesta(respuesta)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionRespuesta datos) {
        var optionalRespuesta = repository.findById(id);
        if (optionalRespuesta.isPresent()){
            var respuesta = optionalRespuesta.get();

            Usuario nuevoAutor = null;
            if (datos.idUsuario() != null) {
                nuevoAutor = usuarioRepository.getReferenceById(datos.idUsuario());
            }

            respuesta.actualizarInformacion(datos, nuevoAutor);
            return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        var optionalRespuesta = repository.findById(id);

        if (optionalRespuesta.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
