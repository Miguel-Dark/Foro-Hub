package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.topico.*;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import com.aluracursos.forohub.infra.errores.ValidacionDeIntegridadException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@Tag(name = "Tópicos", description = "Operaciones para crear, listar y gestionar dudas en el foro")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    @PostMapping
    @Operation(summary = "Registra un nuevo tópico", description = "Requiere un token válido. Envía título, mensaje y autor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tópico creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o tópico duplicado"),
            @ApiResponse(responseCode = "403", description = "No autorizado - Token inválido o ausente")
    })
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos,
                                    UriComponentsBuilder uriComponentsBuilder){
        if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidacionDeIntegridadException("Este tópico ya existe (título y mensaje duplicados)");
        }
        var usuario = usuarioRepository.getReferenceById(datos.idUsuario());
        var curso = cursoRepository.getReferenceById(datos.idCurso());
        var topico = repository.save(new Topico(datos, usuario, curso));

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    @Operation(summary = "Lista todos los tópicos", description = "Obtiene un listado paginado de los tópicos del foro.")
    @ApiResponse(responseCode = "200", description = "Listado de tópicos obtenido correctamente")
    public ResponseEntity<Page<DatosListaTopico>> listar(@PageableDefault(
            size=10, sort={"fechaCreacion"}, direction = Sort.Direction.ASC) Pageable paginacion) {
        var page = repository.findAll(paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un tópico específico", description = "Busca un tópico por su ID y devuelve sus detalles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tópico encontrado"),
            @ApiResponse(responseCode = "404", description = "Tópico no encontrado")
    })
    public ResponseEntity detallar(@PathVariable Long id) {
        var optionalTopico = repository.findById(id);

        if (optionalTopico.isPresent()) {
            var topico = optionalTopico.get();
            return ResponseEntity.ok(new DatosDetalleTopico(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un tópico existente", description = "Permite modificar el título, mensaje, autor o curso de un tópico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tópico actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Tópico no encontrado")
    })
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos) {
        var optionalTopico = repository.findById(id);
        if (optionalTopico.isPresent()) {
            var topico = optionalTopico.get();

            Usuario nuevoAutor = null;
            if (datos.idUsuario() != null) {
                nuevoAutor = usuarioRepository.getReferenceById(datos.idUsuario());
            }

            Curso nuevoCurso = null;
            if (datos.idCurso() != null) {
                nuevoCurso = cursoRepository.getReferenceById(datos.idCurso());
            }

            topico.actualizarInformacion(datos, nuevoAutor, nuevoCurso);
            return ResponseEntity.ok(new DatosDetalleTopico(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un tópico", description = "Elimina permanentemente un tópico de la base de datos por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tópico eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Tópico no encontrado")
    })
    public ResponseEntity eliminar(@PathVariable Long id) {
        var topico = repository.findById(id);

        if (topico.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping("/{id}/cerrar")
    @Operation(summary = "Cierra un tópico", description = "Cambia el estado del tópico a cerrado.")
    @ApiResponse(responseCode = "200", description = "Tópico cerrado correctamente")
    public ResponseEntity cerrar(@PathVariable Long id) {
        var optionalTopico = repository.findById(id);

        if (optionalTopico.isPresent()) {
            var topico = optionalTopico.get();
            topico.cerrarTopico();
            return ResponseEntity.ok(new DatosDetalleTopico(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping("/{id}/solucionar")
    @Operation(summary = "Marca como solucionado", description = "Cambia el estado del tópico para indicar que ya tiene una respuesta válida.")
    @ApiResponse(responseCode = "200", description = "Tópico marcado como solucionado")
    public ResponseEntity solucionar(@PathVariable Long id) {
        var optionalTopico = repository.findById(id);

        if (optionalTopico.isPresent()) {
            var topico = optionalTopico.get();
            topico.marcarComoSolucionado();
            return ResponseEntity.ok(new DatosDetalleTopico(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filtrar")
    @Operation(summary = "Filtra tópicos por estado", description = "Devuelve una lista paginada de tópicos filtrados por su estatus (Abierto, Cerrado, etc.).")
    @ApiResponse(responseCode = "200", description = "Filtro aplicado con éxito")
    public ResponseEntity<Page<DatosListaTopico>> filtrarPorStatus(
            @RequestParam StatusTopico status,
            @PageableDefault(size = 10) Pageable paginacion) {

        var page = repository.findAllByStatus(status, paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }
}
