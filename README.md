# API de Gestión de Personas - Backend

Este proyecto es una API RESTful desarrollada con Java y Spring Boot, 
diseñada para servir como backend para una aplicación de gestión de personas y sus direcciones. 
La API maneja la lógica de negocio, la persistencia de datos y la exposición de endpoints para operaciones CRUD completas.

## Características Principales

- **CRUD de Personas:** Endpoints para crear, leer, actualizar y eliminar registros de personas.
- **CRUD de Direcciones:** Endpoints para añadir, leer, actualizar y eliminar la dirección asociada a una persona **(relación uno a uno).**
- **Validación de Datos:**
    - Utiliza Jakarta Bean Validation para validar los datos de entrada en los DTOs (ej. `@NotBlank`, `@NotNull`).
    - Incluye una **validación personalizada para el RUT chileno** (`@ValidateRut`) que verifica el formato y el dígito verificador.
- **Manejo de Errores Centralizado:** Un `GlobalExceptionHandler` captura excepciones en toda la aplicación (errores de validación, recursos no encontrados, errores del servidor) y devuelve respuestas JSON consistentes y claras.
- **Documentación de API con Swagger:** La API está auto-documentada usando OpenAPI 3 (Swagger). Esto genera una interfaz de usuario interactiva para explorar y probar los endpoints.
- **Base de Datos en Memoria:** Utiliza H2 Database para la persistencia de datos, configurada para crearse y destruirse al iniciar y detener la aplicación (`create-drop`), ideal para desarrollo y pruebas.
- **Población Inicial de Datos:** Incluye una clase `DataLoader` que inserta un registro de ejemplo al iniciar la aplicación para facilitar las pruebas iniciales.

## Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3:** Framework principal para la creación de la aplicación.
- **Spring Web:** Para construir los controladores REST.
- **Spring Data JPA:** Para la capa de persistencia de datos con el repositorio.
- **Hibernate:** Implementación de JPA para el mapeo objeto-relacional (ORM).
- **H2 Database:** Base de datos en memoria para un desarrollo rápido.
- **Lombok:** Para reducir el código repetitivo en las clases (constructores, getters, setters, etc.).
- **Jakarta Bean Validation:** Para las validaciones de los DTOs.
- **SpringDoc OpenAPI (Swagger):** Para la documentación automática de la API.

## Arquitectura del Proyecto

El proyecto está estructurado en una arquitectura por capas para separar responsabilidades y mejorar la mantenibilidad:

- **`controller`**: Recibe las peticiones HTTP, valida los datos de entrada y las delega a la capa de servicio.
- **`service`**: Contiene la lógica de negocio principal de la aplicación.
- **`repository`**: Define las interfaces para el acceso a datos, extendiendo de `JpaRepository`.
- **`entity`**: Define los modelos de datos que se mapean a las tablas de la base de datos (Person, Address).
- **`dto`**: (Data Transfer Objects) Clases `record` para modelar los datos de las peticiones (`request`) y respuestas (`response`) de la API.
- **`mapper`**: Componentes encargados de convertir entre DTOs y entidades.
- **`exception`**: Clases para el manejo de errores, incluyendo excepciones personalizadas y un manejador global.
- **`validation`**: Lógica para las validaciones personalizadas, como la del RUT.
- **`enums`**: Lógica manejar errores normalizados al cliente y otros.

## Cómo Ejecutar el Proyecto

### Requisitos
- JDK 21 o superior.
- Ide como visual studio code o IntelliJ Idea
- Maven instalado (opcional, viene incrustado en spring boot)

### Pasos

1.  **Clona el repositorio**
    ```sh
    git clone https://github.com/bastianleond/com.bastianleond.backend.react.sermulac.test.git
    ```

2.  **Navega a la carpeta del proyecto**
    ```sh
    cd nombre-del-proyecto
    ```

3.  **Ejecuta la aplicación con Maven**
    ```sh
    mvn spring-boot:run
    ```
    La API se iniciará en `http://localhost:8080`.

4. **Si va a ser a nivel de desarrollo pueden correr el programa con IntelliJ Idea o visual Studio o el ide de preferencia** 



## Endpoints Disponibles

Una vez que la aplicación esté corriendo, puedes acceder a los siguientes recursos:

- **Documentación de la API (Swagger UI):**
    - [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **Documentación de para cliente (Redocly):**
    - [http://localhost:8080/documentation.html](http://localhost:8080/documentation.html)

- **Consola de la Base de Datos H2:**
    - **URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    - **JDBC URL:** `jdbc:h2:mem:testprevireddb`
    - **User Name:** `sa`
    - **Password:** (dejar en blanco)

### Principales Endpoints de la API

- `POST /api/1.0/people`: Crea una nueva persona.
- `GET /api/1.0/people`: Obtiene una lista de todas las personas.
- `GET /api/1.0/people/uuid/{uuid}`: Obtiene una persona por su UUID.
- `PUT /api/1.0/people`: Actualiza los datos de una persona.
- `DELETE /api/1.0/people/uuid/{uuid}`: Elimina una persona.
- `POST /api/1.0/people/address/add`: Añade una dirección a una persona.
- `PUT /api/1.0/people/address`: Actualiza una dirección.
- `DELETE /api/1.0/people/address/uuid/{uuid}`: Elimina una dirección.

**Por tiempo de la prueba se omitieron pruebas de todo tipo**

Bastián León.