package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.curso.DatosListaCurso;
import com.aluracursos.forohub.domain.curso.DatosRegistroCurso;
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
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroCurso datos){
        cursoRepository.save(new Curso(datos));
    }

    @GetMapping
    public Page<DatosListaCurso> listar(@PageableDefault(size=10, sort={"nombre"}) Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(DatosListaCurso::new);
    }
}
