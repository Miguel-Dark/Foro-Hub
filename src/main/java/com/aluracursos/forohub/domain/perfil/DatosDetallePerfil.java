package com.aluracursos.forohub.domain.perfil;

public record DatosDetallePerfil(
        Long id,
        String nombre
) {
    public DatosDetallePerfil(Perfil perfil) {
        this(perfil.getId(), perfil.getNombre());
    }
}
