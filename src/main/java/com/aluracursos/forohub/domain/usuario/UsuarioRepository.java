package com.aluracursos.forohub.domain.usuario;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);
    Usuario findByNombre(String nombre);

    @Query("SELECT COUNT(t) FROM Topico t WHERE t.autor.id = :usuarioId")
    long countTopicosPorUsuario(Long usuarioId);

    @Query("SELECT COUNT(r) FROM Respuesta r WHERE r.autor.id = :usuarioId AND r.solucion = true")
    long countSolucionesPorUsuario(Long usuarioId);

    @Query("""
    SELECT r.autor FROM Respuesta r
    WHERE r.solucion = true
    GROUP BY r.autor
    ORDER BY COUNT(r) DESC
""")
    List<Usuario> findTop3BySoluciones(Pageable pageable);
}
