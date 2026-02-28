package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.usuario.DatosListaUsuario;
import com.aluracursos.forohub.domain.usuario.DatosRegistroUsuario;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroUsuario datos){
        usuarioRepository.save(new Usuario(datos));
    }

    @GetMapping
    public Page<DatosListaUsuario> listar(@PageableDefault(size=10, sort={"nombre"}) Pageable paginacion) {
        return usuarioRepository.findAll(paginacion).map(DatosListaUsuario::new);
    }
}
