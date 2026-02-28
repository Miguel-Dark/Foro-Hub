package com.aluracursos.forohub.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean activo;
    private String usuario;
    private String mensaje;
    private String nombreCurso;
    private String titulo;

    public Topico(DatosRegistroTopico datos) {
        this.id = null;
        this.activo = true;
        this.usuario = datos.usuario();
        this.mensaje = datos.mensaje();
        this.nombreCurso = datos.nombreCurso();
        this.titulo = datos.titulo();
    }

}
