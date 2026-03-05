package com.aluracursos.forohub.domain.usuario;

import com.aluracursos.forohub.domain.perfil.Perfil;

import java.util.List;

public record DatosListaUsuario(
        Long id,
        String nombre,
        String email,
        List<String> perfiles
) {
    public DatosListaUsuario(Usuario usuario) { //constructor
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPerfiles().stream()
                        .map(Perfil::getNombre)
                        .toList()
        );
    }
}
