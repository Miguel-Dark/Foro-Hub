package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.respuesta.DatosListaRespuesta;
import com.aluracursos.forohub.domain.respuesta.DatosRegistroRespuesta;
import com.aluracursos.forohub.domain.respuesta.Respuesta;
import com.aluracursos.forohub.domain.respuesta.RespuestaRepository;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<DatosListaRespuesta> listar(@PageableDefault(size=10, sort={"fechaCreacion"}) Pageable paginacion) {
        return repository.findAll(paginacion).map(DatosListaRespuesta::new);
    }
}
