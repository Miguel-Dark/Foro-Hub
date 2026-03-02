package com.aluracursos.forohub.domain.perfil;


public record DatosListaPerfil(
        Long id,
        String nombre
) {
    public DatosListaPerfil(Perfil perfil) { //constructor
        this(
                perfil.getId(),
                perfil.getNombre()
        );
    }
}
