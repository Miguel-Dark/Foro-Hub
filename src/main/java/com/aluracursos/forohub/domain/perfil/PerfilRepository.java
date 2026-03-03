package com.aluracursos.forohub.domain.perfil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Perfil findByNombre(String nombre);
    Page<Perfil> findAllByActivoTrue(Pageable paginacion);
}
