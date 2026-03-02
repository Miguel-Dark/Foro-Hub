package com.aluracursos.forohub.domain.usuario;

import com.aluracursos.forohub.domain.perfil.Perfil;

import java.util.List;

public record DatosDetalleUsuario(
        Long id,
        String nombre,
        String email,
        List<Perfil> perfiles
) {
    public DatosDetalleUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPerfiles()
        );
    }
}
