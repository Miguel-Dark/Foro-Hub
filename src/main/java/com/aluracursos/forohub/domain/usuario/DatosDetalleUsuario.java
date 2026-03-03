package com.aluracursos.forohub.domain.usuario;

public record DatosDetalleUsuario(
        Long id,
        String nombre,
        String email,
        String nombrePerfil
) {
    public DatosDetalleUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPerfiles().get(0).getNombre()
        );
    }
}
