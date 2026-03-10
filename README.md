# Spring Framework Challenge - ForoHub

<p align="center">
  <img src="assets/banner-forohub.png" alt="ForoHub Banner" width="100%">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java" alt="Java">
  <img src="https://img.shields.io/badge/Spring_Boot-3.4.3-brightgreen?style=for-the-badge&logo=spring-boot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/PostgreSQL-17-blue?style=for-the-badge&logo=postgresql" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Maven-4.0-red?style=for-the-badge&logo=apache-maven" alt="Maven">
  <img src="https://img.shields.io/badge/Spring_Security-6.0-green?style=for-the-badge&logo=spring-security" alt="Spring Security">
</p>

## 📝 Descripción del Proyecto

**ForoHub** es una solución Backend desarrollada bajo el ecosistema de Spring Framework, diseñada para la gestión integral de un foro de discusión técnico. El proyecto cumple con los requerimientos del Challenge de Alura Latam y Oracle Next Education (ONE), implementando un sistema persistente para la administración de tópicos, respuestas, usuarios y cursos.

La aplicación garantiza la integridad de los datos mediante validaciones estrictas y un sistema de seguridad robusto que protege la información sensible y restringe el acceso según el rol del usuario (Estudiante o Instructor).

## 🎥 Demostración y Pruebas (Video)

<p align="center">
  <table border="0">
    <tr>
      <td align="center"><b>1. Gestión de Tópicos</b></td>
      <td align="center"><b>2. Seguridad y JWT</b></td>
      <td align="center"><b>3. Administración de Usuarios</b></td>
    </tr>
    <tr>
      <td align="center"><a href="URL_VIDEO_1"><img src="https://img.shields.io/badge/YouTube-FF0000?style=for-the-badge&logo=youtube&logoColor=white" alt="Video 1"></a></td>
      <td align="center"><a href="URL_VIDEO_2"><img src="https://img.shields.io/badge/YouTube-FF0000?style=for-the-badge&logo=youtube&logoColor=white" alt="Video 2"></a></td>
      <td align="center"><a href="URL_VIDEO_3"><img src="https://img.shields.io/badge/YouTube-FF0000?style=for-the-badge&logo=youtube&logoColor=white" alt="Video 3"></a></td>
    </tr>
    <tr>
      <td align="center"><b>4. Cursos y Respuestas</b></td>
      <td align="center"><b>Documentación</b></td>
    </tr>
    <tr>
      <td align="center"><a href="URL_VIDEO_4"><img src="https://img.shields.io/badge/YouTube-FF0000?style=for-the-badge&logo=youtube&logoColor=white" alt="Video 4"></a></td>
      <td align="center"><a href="URL_VIDEO_5"><img src="https://img.shields.io/badge/YouTube-FF0000?style=for-the-badge&logo=youtube&logoColor=white" alt="Video 5"></a></td>
      <td align="center"><i>Próximamente</i></td>
    </tr>
  </table>
</p>

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 17 (JDK)
* **Framework:** Spring Boot 3.4.3
* **Gestión de Dependencias:** Maven
* **Base de Datos:** PostgreSQL 17
* **Persistencia:** Spring Data JPA
* **Migraciones:** Flyway
* **Seguridad:** Spring Security (Stateless) con autenticación JWT
* **Documentación:** Swagger / OpenAPI
* **Cliente de Pruebas:** Insomnia

## 🔐 Implementación de Seguridad

La seguridad se basa en una arquitectura de autenticación **Stateless** mediante tokens JWT. Se han definido permisos granulares para garantizar que cada recurso sea accedido únicamente por los perfiles autorizados:

* **Consultas (GET):** Acceso permitido a cualquier usuario autenticado.
* **Gestión de Tópicos (POST/PUT):** Exclusivo para el rol de Estudiante e Instructor.
* **Moderación y Administración (DELETE):** Acceso restringido únicamente al rol de Instructor.
* **Gestión de Cursos y Usuarios:** Rutas administrativas protegidas bajo autoridad de Instructor.

## 📊 Arquitectura de Datos

El diseño de la base de datos sigue el modelo relacional, utilizando **PostgreSQL** y **Flyway** para el control de versiones del esquema. Esto asegura que la base de datos sea escalable y fácil de mantener a medida que el foro crece.

## 🚀 Instalación y Uso

1. Clonar el repositorio.
2. Crear la base de datos en PostgreSQL.
3. Configurar las credenciales de acceso en el archivo de propiedades del proyecto.
4. Ejecutar el comando: `mvn spring-boot:run`

---

## 👨‍💻 Autor

Desarrollado por **Miguel Àngel de la Cruz Làzaro**.
