package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private Boolean solucion;

    public Respuesta(String mensaje, Usuario autor, Topico topico) {
        this.id = null;
        this.mensaje = mensaje;
        this.autor = autor;
        this.topico = topico;
        this.fechaCreacion = LocalDateTime.now();
        this.solucion = false;
    }

    public void actualizarInformacion(@Valid DatosActualizacionRespuesta datos, Usuario nuevoAutor, Topico nuevoTopico) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (nuevoAutor != null) {
            this.autor = nuevoAutor;
        }
        if (nuevoTopico != null) {
            this.topico = nuevoTopico;
        }
        if (datos.solucion() != null) {
            this.solucion = datos.solucion();
        }
    }

    public void marcarComoSolucion() {
        this.solucion = true;
        this.topico.marcarComoSolucionado();
    }
}
