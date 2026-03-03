package com.aluracursos.forohub.domain.perfil;

import com.aluracursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "perfiles")
@Entity(name = "Perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToMany(mappedBy = "perfiles")
    private List<Usuario> usuarios = new ArrayList<>();


    public Perfil(String nombre) {
        this.nombre = nombre;
    }

    public Perfil(DatosRegistroPerfil datos) {

        this.nombre = datos.nombre();
    }

    public void actualizarInformacion(@Valid DatosActualizarPerfil datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
    }
}
