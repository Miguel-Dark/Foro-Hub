package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.topico.DatosRegistroTopico;
import com.aluracursos.forohub.topico.Topico;
import com.aluracursos.forohub.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
