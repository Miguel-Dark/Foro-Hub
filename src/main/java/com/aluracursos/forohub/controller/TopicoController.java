package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topico.DatosListaTopico;
import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroTopico datos){
        var usuario = usuarioRepository.getReferenceById(datos.usuario());
        repository.save(new Topico(datos, usuario));
    }

    @GetMapping
    public Page<DatosListaTopico> listar(@PageableDefault(size=10, sort={"idUsuario"}) Pageable paginacion) {
        return repository.findAll(paginacion).map(DatosListaTopico::new);
    }
}
