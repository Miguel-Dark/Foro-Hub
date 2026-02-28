package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.topico.DatosListaTopico;
import com.aluracursos.forohub.topico.DatosRegistroTopico;
import com.aluracursos.forohub.topico.Topico;
import com.aluracursos.forohub.topico.TopicoRepository;
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

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroTopico datos){
        repository.save(new Topico(datos));
    }

    @GetMapping
    public Page<DatosListaTopico> listar(@PageableDefault(size=10, sort={"usuario"}) Pageable paginacion) {
        return repository.findAll(paginacion).map(DatosListaTopico::new);
    }
}
