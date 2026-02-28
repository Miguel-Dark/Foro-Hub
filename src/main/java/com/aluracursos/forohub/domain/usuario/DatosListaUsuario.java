package com.aluracursos.forohub.domain.usuario;

public record DatosListaUsuario(
        Long id,
        String nombre,
        String email,
        Perfil perfil
) {
    public DatosListaUsuario(Usuario usuario) { //constructor
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPerfil()
        );
    }
}
