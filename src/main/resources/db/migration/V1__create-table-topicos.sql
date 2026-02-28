CREATE TABLE topicos (
    id BIGSERIAL PRIMARY KEY,
    activo BOOLEAN NOT NULL,
    usuario VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    nombre_curso VARCHAR(255) NOT NULL,
    titulo VARCHAR(255) NOT NULL
);