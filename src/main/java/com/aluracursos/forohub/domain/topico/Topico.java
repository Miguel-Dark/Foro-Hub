package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.usuario.Usuario;
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

    @ManyToOne
    private Usuario usuario;

    private String mensaje;
    private String nombreCurso;
    private String titulo;

    public Topico(DatosRegistroTopico datos, Usuario usuario) {
        this.id = null;
        this.activo = true;
        this.usuario = usuario;
        this.mensaje = datos.mensaje();
        this.nombreCurso = datos.nombreCurso();
        this.titulo = datos.titulo();
    }

}
