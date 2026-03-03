CREATE TABLE respuestas (
    id BIGSERIAL PRIMARY KEY,
    mensaje TEXT NOT NULL,
    topico_id BIGINT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    autor_id BIGINT NOT NULL,
    solucion BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT fk_respuestas_topico FOREIGN KEY (topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_respuestas_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);