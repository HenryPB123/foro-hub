# 📍 Foro Hub

Foro Hub es una API REST desarrollada en **Java con Spring Boot** para gestionar un foro de discusión, donde los usuarios pueden:

✔ Registrarse e iniciar sesión  
✔ Crear cursos  
✔ Publicar temas (topics)  
✔ Responder a temas  
✔ Marcar una respuesta como solución  
✔ Eliminar respuestas  
✔ Paginación de recursos  
✔ Seguridad con JWT

Este proyecto fue desarrollado como parte de **un desafío del área de Backend del programa ALURA (Alura Challenge)** y aplica buenas prácticas de diseño, arquitectura y seguridad.

---

## 🧠 Tecnologías usadas

| Tecnología         | Versión / Uso                     |
|-------------------|-----------------------------------|
| Java              | 17                                |
| Spring Boot       | Framework principal               |
| Spring Security   | Para autenticación y autorización |
| JWT               | Autenticación basada en tokens    |
| Spring Data JPA   | Persistencia con Hibernate        |
| MySQL             | Base de datos                     |
| Flyway            | Migrations                        |
| Lombok            | Reducción de código repetitivo    |
| Swagger/OpenAPI   | Documentación de la API           |

---

## 🚀 Funcionalidades principales

### 📌 Usuarios
- Registro de usuario
- Login con JWT
- Roles y permisos
- Autenticación segura

---

### 📌 Cursos
- CRUD de cursos
- Regla de negocio: nombres únicos
- Categorías definidas

---

### 📌 Topics
- Crear temas por curso
- Listar topics paginados
- Regla de negocio: títulos únicos
- Obtener detalle de un topic

---

### 📌 Respuestas (Answers)
- Responder un topic
- Listar respuestas de un topic
- Marcar una respuesta como solución
- Eliminar respuestas (autor de la respuesta o autor del topic)

---

## 🧩 Reglas de negocio destacadas

✔ Uncurso no puede repetirse por nombre  
✔ Un topic no puede repetirse por título  
✔ Solo el autor del topic puede marcar una solución  
✔ Solo el autor de la respuesta o el autor del topic pueden eliminar una respuesta

---

## 📁 Estructura de endpoints (breve)

### 🔐 Usuarios

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | /users | Crear usuario |
| GET | /users | Listar usuarios paginados |
| GET | /users/{id} | Obtener usuario por ID |
| PUT | /users | Actualizar usuario |
| DELETE | /users/{id} | Eliminar usuario |

---

### 🧑‍🏫 Cursos

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | /courses | Crear curso |
| GET | /courses | Listar cursos |
| GET | /courses/{id} | Obtener curso |
| PUT | /courses/{id} | Actualizar curso |
| DELETE | /courses/{id} | Eliminar curso |

---

### 💬 Topics

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | /topics | Crear Topic |
| GET | /topics | Listar topics |
| GET | /topics/{id} | Obtener topic |
| PUT | /topics/{id} | Actualizar topic |
| DELETE | /topics/{id} | Eliminar topic |

---

### 💡 Answers

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | /topics/{topicId}/answers | Crear respuesta |
| GET | /topics/{topicId}/answers | Listar respuestas |
| PUT | /answers/{id}/solution | Marcar como solución |
| PUT | /answers/{id} | Actualizar respuesta |
| DELETE | /answers/{id} | Eliminar respuesta |

---

## 🗺 Documentación de API

La API viene documentada con **Swagger / OpenAPI**.

📌 Una vez que el proyecto está ejecutándose, puedes acceder a la documentación en: http://localhost:8080/swagger-ui/index.html

Ahí podrás ver todos los endpoints y probar las peticiones desde el navegador.

---

## 🛠 Configuración de la base de datos

Este proyecto usa **MySQL** como base de datos.  
Configura **application.properties** o **application.yml** con tus datos:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

spring.flyway.enabled=true

🔐 Seguridad

La API está protegida con JWT (JSON Web Tokens).
Para acceder a la mayoría de endpoints necesitas un token válido generado desde:

POST /login

✨ Siguientes pasos que puedes implementar

✔ Tests automatizados (JUnit + Mockito)
✔ Gestión de roles más fina (ADMIN / USER)
✔ Mejor manejo de errores detallados
✔ Cache para rutas de lectura (Redis)

📌 ¿Por qué este proyecto es valioso?

Foro Hub no es un CRUD común.
Integra aspectos reales de backend:

🧠 Reglas de negocio
🔐 Seguridad con JWT
💾 Persistencia relacional con migraciones
📊 Paginación
📜 Documentación profesional

Además, forma parte de un challenge del área de Backend de ALURA, por lo que cumple criterios evaluados de calidad, arquitectura y buenas prácticas.

📎 Link del repositorio

👉 https://github.com/HenryPB123/foro-hub

🧡 ¡Gracias por visitar este proyecto!