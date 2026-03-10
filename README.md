#  Spring Framework: Challenge Foro Hub
### By Miguel Ángel de la Cruz Lázaro

<p align="center">
  <img src="assets/banner-forohub.png" alt="ForoHub Banner" width="100%">
</p>

---

### 📝 Descripción del Proyecto
**ForoHub** es una solución Backend robusta diseñada para gestionar un foro de discusión dinámico. El proyecto nace como un desafío de **Alura Latam**, donde el objetivo principal fue aplicar conocimientos avanzados de **Java y Spring Boot** para crear una API REST escalable. 

Esta API integra capas de seguridad industrial, migraciones de base de datos automatizadas y una lógica de negocio que garantiza la integridad de la información, reflejando un desarrollo orientado a la **Engineering Excellence**.

### 🛠️ Tech Stack & Initializr Dependencies

**Core Engine & Environment:**
<p align="left">
  <img src="https://img.shields.io/badge/Java_17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring_Boot_3.4.3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot 3.4.3">
  <img src="https://img.shields.io/badge/Maven_4-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven 4">
  <img src="https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white" alt="IntelliJ">
</p>

**Project Dependencies (Spring Initializr):**
<p align="left">
  <img src="https://img.shields.io/badge/Spring_Web-6DB33F?style=flat-square&logo=spring&logoColor=white" alt="Web">
  <img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=flat-square&logo=spring&logoColor=white" alt="JPA">
  <img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=flat-square&logo=spring-security&logoColor=white" alt="Security">
  <img src="https://img.shields.io/badge/PostgreSQL_Driver-4169E1?style=flat-square&logo=postgresql&logoColor=white" alt="Postgres">
  <img src="https://img.shields.io/badge/Flyway_Migration-CC0202?style=flat-square&logo=flyway&logoColor=white" alt="Flyway">
  <img src="https://img.shields.io/badge/Lombok-BC133E?style=flat-square&logo=lombok&logoColor=white" alt="Lombok">
  <img src="https://img.shields.io/badge/Validation-5849BE?style=flat-square&logo=hibernate&logoColor=white" alt="Validation">
  <img src="https://img.shields.io/badge/DevTools-6DB33F?style=flat-square&logo=spring&logoColor=white" alt="DevTools">
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
