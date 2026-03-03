package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.infra.errores.ValidacionDeIntegridadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Transactional
    public void marcarComoSolucion(Long respuestaId) {

        var respuesta = respuestaRepository.findById(respuestaId)
                .orElseThrow(() -> new ValidacionDeIntegridadException("No se encontró la respuesta con el ID proporcionado"));

        respuesta.marcarComoSolucion();
    }
}
