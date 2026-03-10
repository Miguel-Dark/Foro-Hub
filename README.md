#  ForoHub: Backend Engineering Excellence
### By Miguel Ángel de la Cruz Lázaro

<p align="center">
  <img src="assets/banner-forohub.png" alt="ForoHub Banner" width="100%">
</p>

---

##  Portafolio de Implementación (Video Demos)
*Haz clic en las insignias para ver la ejecución técnica en Insomnia:*

###  Gestión de Núcleo
> **Video 1: Corazón y Reglas de Negocio** > CRUD completo de Tópicos, validaciones de Trello y manejo de roles.  
> [![Ver Demo 1](https://img.shields.io/badge/EJECUTAR_DEMO_01-FF0000?style=for-the-badge&logo=youtube&logoColor=white)](https://youtu.be/gDr9i5kYcsk)

###  Capa de Seguridad
> **Video 2: Blindaje Spring Security & JWT** > Demostración de autenticación Stateless y protección de endpoints.  
> [![Ver Demo 2](https://img.shields.io/badge/EJECUTAR_DEMO_02-FF0000?style=for-the-badge&logo=youtube&logoColor=white)](https://youtu.be/jN9BPTK6T_A)

###  Administración de Entidades
> **Video 3: Usuarios, Cursos y BCrypt** > Gestión de perfiles y persistencia de seguridad en base de datos.  
> [![Ver Demo 3](https://img.shields.io/badge/EJECUTAR_DEMO_03-FF0000?style=for-the-badge&logo=youtube&logoColor=white)](https://youtu.be/puCDjZjR4w4)

###  Lógica de Dominio Avanzada
> **Video 4: Ciclo de Vida y Soluciones** > Interacción entre respuestas, estados dinámicos y lógica de negocio.  
> [![Ver Demo 4](https://img.shields.io/badge/EJECUTAR_DEMO_04-FF0000?style=for-the-badge&logo=youtube&logoColor=white)](https://youtu.be/x7l4goOg764)

###  Cierre de Proyecto
> **Video 5: Perfiles, Métricas Públicas y DB Final** > Resumen de estadísticas, acceso PermitAll y esquema PostgreSQL.  
> [![Ver Demo 5](https://img.shields.io/badge/VER_CIERRE_MAESTRO-FF0000?style=for-the-badge&logo=youtube&logoColor=white)](https://youtu.be/sjPwkXBKvrk)

---

## Seguridad y Buenas Prácticas (Ciberseguridad)
No es solo código, es blindaje profesional. He implementado:

* **Anti-Injection Layer:** Uso estricto de **Prepared Statements** mediante Spring Data JPA para neutralizar SQL Injection. 
* **Gatekeeper Validation:** Filtro de integridad con **Hibernate Validator** (@NotBlank, @Valid) para detener datos corruptos en la entrada. 
* **Stateless Fortress:** Arquitectura de seguridad basada en **JWT Tokens**, eliminando la vulnerabilidad de sesiones. 
* **Data Integrity:** Migraciones controladas con **Flyway**, asegurando que la base de datos sea una bóveda inmutable. 
* **Information Exposure Control:** Centralización de errores con `@RestControllerAdvice` para ocultar Stack Traces sensibles. 

---

##  Funcionalidades de Alto Nivel

###  Gamificación & Lógica Pro
* **Ranking Dinámico:** Sistema que asigna rangos (Estudiante, Colaborador, Instructor Senior) según el impacto del usuario.
* **Sincronización de Estados:** Al marcar una "Respuesta Ganadora", el sistema dispara un evento atómico que actualiza el Tópico a **SOLUCIONADO**.
* **Soft Delete:** Implementación de borrado lógico en Perfiles y Cursos para auditoría e integridad histórica.

###  Arquitectura DDD Lite
Dividida para escalabilidad:
1.  **Domain:** Entidades puras con lógica de negocio encapsulada.
2.  **Controller:** Interfaz REST con 9 puntos de entrada optimizados.
3.  **Infra:** Persistencia en PostgreSQL y documentación con Swagger.

---

##  Stack Tecnológico
* **Java 17 / Spring Boot 3.4.3**
* **PostgreSQL 17 / Spring Data JPA**
* **Spring Security & JWT**
* **Flyway / Maven / Lombok**
* **Swagger (OpenAPI) / Insomnia**

---

##  Conclusión
Este proyecto representa la culminación técnica de mi paso por **Oracle Next Education** y **Alura Latam**. Es una muestra de arquitectura Backend lista para producción.

**Desarrollado con precisión por:** **Miguel Ángel de la Cruz Lázaro**
