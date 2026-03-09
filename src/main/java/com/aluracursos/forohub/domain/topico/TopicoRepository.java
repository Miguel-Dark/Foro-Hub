package com.aluracursos.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAll(Pageable paginacion);
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    long countByStatus(StatusTopico status);

    @Query("SELECT COUNT(t) FROM Topico t WHERE t.respuestas IS EMPTY")
    long countBySinRespuestas();

    Page<Topico> findAllByStatus(StatusTopico status, Pageable paginacion);
}
