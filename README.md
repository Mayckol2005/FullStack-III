# Colegio Bernardo O'Higgins — Backend Plataforma de Gestión Escolar

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-2023.0.1-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

Backend distribuido de la plataforma de gestión escolar del **Colegio Bernardo O'Higgins**, desarrollado con Java, Spring Boot y Spring Cloud.

El ecosistema implementa servicios independientes para autenticación, usuarios, estudiantes, gestión académica, evaluaciones, asistencia, anotaciones, comunicaciones, notificaciones y reportes.

La arquitectura utiliza API Gateway, Service Discovery, configuración centralizada, JWT, OpenFeign, Redis, RabbitMQ, Swagger/OpenAPI y Docker Compose.

---

# Integrantes

- Martín Baza
- Mayckol Mardones
- Francisco Vera

---

# Objetivo del backend

Desarrollar una arquitectura backend distribuida que permita gestionar los procesos académicos y administrativos de la plataforma escolar mediante servicios desacoplados.

El ecosistema contempla:

- Autenticación de usuarios.
- Gestión de usuarios y roles.
- Gestión de estudiantes.
- Gestión de cursos y asignaturas.
- Registro de evaluaciones.
- Control de asistencia.
- Gestión de anotaciones.
- Comunicaciones institucionales.
- Procesamiento asíncrono de mensajes.
- Agregación de información académica.
- Seguridad mediante JWT.
- Descubrimiento dinámico de servicios.
- Configuración centralizada.

---

# Arquitectura del sistema

La plataforma utiliza una arquitectura basada en microservicios y componentes distribuidos.

```txt
Frontend React
      │
      ▼
┌─────────────────────┐
│     API Gateway     │
│       :8080         │
└──────────┬──────────┘
           │
           │ Service Discovery
           ▼
┌─────────────────────┐
│    Eureka Server    │
│       :8761         │
└─────────────────────┘

           │
           ├── auth-service
           ├── usuario-service
           ├── estudiante-service
           ├── academico-service
           ├── evaluacion-service
           ├── asistencia-service
           ├── anotacion-service
           ├── comunicacion-service
           ├── notificacion-service
           └── reporte-service
                    │
                    └── Backend For Frontend

Infraestructura
      │
      ├── Config Server
      ├── MySQL
      ├── Redis
      └── RabbitMQ
```

El tráfico de la aplicación se centraliza mediante el API Gateway.

Los servicios se registran en Eureka y utilizan Spring Cloud para resolver la comunicación interna dentro del ecosistema.

---

# Componentes principales

| Componente | Responsabilidad |
|---|---|
| `api-gateway` | Punto de entrada y enrutamiento del tráfico |
| `eureka-server` | Descubrimiento y registro de servicios |
| `config-server` | Configuración centralizada |
| `auth-service` | Autenticación y generación de JWT |
| `usuario-service` | Gestión de usuarios y roles |
| `estudiante-service` | Gestión de estudiantes |
| `academico-service` | Gestión de cursos y asignaturas |
| `evaluacion-service` | Registro y consulta de evaluaciones |
| `asistencia-service` | Control de asistencia |
| `anotacion-service` | Gestión de anotaciones |
| `comunicacion-service` | Gestión de avisos institucionales |
| `notificacion-service` | Servicio asociado al dominio de notificaciones |
| `reporte-service` | Backend For Frontend y agregación de información |

---

# Estructura del proyecto

```txt
FullStack-III/
│
├── academico-service/
├── anotacion-service/
├── api-gateway/
├── asistencia-service/
├── auth-service/
├── comunicacion-service/
├── config-server/
├── estudiante-service/
├── eureka-server/
├── evaluacion-service/
├── notificacion-service/
├── reporte-service/
├── usuario-service/
│
├── docker-compose.yml
└── README.md
```

Cada servicio Spring mantiene su propio ciclo de construcción y dependencias Maven.

---

# Arquitectura interna de los servicios

Los servicios funcionales utilizan una organización en capas.

```txt
Microservicio
│
├── controller/
├── service/
├── repository/
├── entity/
├── dto/
├── client/
├── config/
├── security/
└── exception/
```

La presencia de cada capa depende de la responsabilidad del servicio.

| Capa | Responsabilidad |
|---|---|
| `Controller` | Exposición de endpoints REST |
| `Service` | Lógica de negocio |
| `Repository` | Persistencia mediante Spring Data JPA |
| `Entity` | Modelado de entidades persistentes |
| `DTO` | Transferencia de información |
| `Client` | Comunicación mediante OpenFeign |
| `Config` | Configuración de componentes |
| `Security` | Procesamiento y validación JWT |
| `Exception` | Manejo centralizado de errores |

---

# Stack tecnológico

## Backend

- Java 21.
- Spring Boot 3.2.5.
- Spring Cloud 2023.0.1.
- Spring Web.
- Spring Data JPA.
- Spring Security.
- Spring Cloud Gateway.
- Spring Cloud Config.
- Netflix Eureka.
- OpenFeign.
- Maven.

## Seguridad

- JSON Web Token.
- Spring Security.
- Filtros JWT.
- Control de acceso basado en roles.
- BCrypt para almacenamiento de contraseñas.

## Persistencia

- MySQL 8.0.
- Spring Data JPA.
- Hibernate.

## Caché

- Redis.
- Spring Cache.

## Mensajería

- RabbitMQ.
- Spring AMQP.

## Documentación de APIs

- OpenAPI.
- Springdoc OpenAPI.
- Swagger UI.

## Testing y cobertura

- JUnit 5.
- Mockito.
- Spring Boot Test.
- JaCoCo.

## Infraestructura

- Docker.
- Docker Compose.

## Control de versiones

- Git.
- GitHub.
- GitHub Flow.

---

# Patrones y decisiones arquitectónicas

| Patrón o componente | Implementación |
|---|---|
| Microservices Architecture | Separación por dominios funcionales |
| API Gateway | `api-gateway` |
| Service Discovery | Eureka Server |
| Centralized Configuration | Spring Cloud Config |
| Backend For Frontend | `reporte-service` |
| Repository Pattern | Spring Data JPA |
| Service Layer | Servicios de lógica de negocio |
| DTO Pattern | Transferencia desacoplada de información |
| Dependency Injection | Contenedor IoC de Spring |
| OpenFeign Client | Comunicación entre servicios |
| Global Exception Handler | `@RestControllerAdvice` |
| Cache Pattern | Spring Cache y Redis |
| Asynchronous Messaging | RabbitMQ y Spring AMQP |

---

# API Gateway

El `api-gateway` constituye el punto central de entrada para las solicitudes de la aplicación.

Puerto local:

```txt
http://localhost:8080
```

Las rutas se resuelven hacia los servicios registrados en Eureka.

Ejemplos:

```txt
/api/auth/**             → auth-service
/api/usuarios/**         → usuario-service
/api/academico/**        → academico-service
/api/estudiantes/**      → estudiante-service
/api/asistencia/**       → asistencia-service
/api/evaluaciones/**     → evaluacion-service
/api/anotaciones/**      → anotacion-service
/api/comunicaciones/**   → comunicacion-service
/api/notificaciones/**   → notificacion-service
/api/reportes/**         → reporte-service
```

Durante el desarrollo local, el Gateway permite solicitudes provenientes del frontend ejecutado en:

```txt
http://localhost:5173
```

---

# Service Discovery con Eureka

Los servicios se registran dinámicamente en Eureka Server.

Eureka se encuentra disponible localmente en:

```txt
http://localhost:8761
```

La comunicación interna puede utilizar identificadores lógicos de servicios en lugar de depender de direcciones IP fijas.

Ejemplo conceptual:

```txt
reporte-service
      │
      ├── estudiante-service
      ├── academico-service
      └── evaluacion-service
```

El API Gateway también utiliza el registro de Eureka para enrutar tráfico hacia los servicios correspondientes.

---

# Configuración centralizada

La plataforma utiliza Spring Cloud Config.

El Config Server se ejecuta en:

```txt
http://localhost:8888
```

Los archivos de configuración centralizada se encuentran en:

```txt
config-server/
└── src/
    └── main/
        └── resources/
            └── config-configurations/
                ├── academico-service.yml
                ├── anotacion-service.yml
                ├── api-gateway.yml
                ├── asistencia-service.yml
                ├── auth-service.yml
                ├── comunicacion-service.yml
                ├── estudiante-service.yml
                ├── evaluacion-service.yml
                ├── notificacion-service.yml
                ├── reporte-service.yml
                └── usuario-service.yml
```

Los servicios utilizan su nombre de aplicación para obtener la configuración correspondiente al iniciar.

---

# Comunicación entre servicios

La comunicación síncrona entre servicios utiliza OpenFeign.

Este mecanismo permite declarar clientes HTTP asociados a servicios registrados en Eureka.

Ejemplo conceptual:

```txt
auth-service
      │
      └── UsuarioFeignClient
              │
              ▼
       usuario-service
```

El `reporte-service` utiliza clientes Feign para obtener información desde otros dominios y generar respuestas agregadas para el consumidor.

En los flujos protegidos, el token de autorización puede propagarse hacia las solicitudes internas que requieren autenticación.

---

# Autenticación y seguridad JWT

La plataforma implementa autenticación stateless basada en JWT.

El flujo principal es:

```txt
Cliente
   │
   │ POST /api/auth/login
   ▼
API Gateway
   │
   ▼
auth-service
   │
   ├── consulta usuario-service
   ├── valida contraseña BCrypt
   └── genera JWT
             │
             ▼
           Cliente
```

Las solicitudes protegidas utilizan:

```http
Authorization: Bearer <token>
```

Los filtros JWT procesan el token y construyen el contexto de autenticación utilizado por Spring Security.

El token contiene información utilizada por los servicios para identificar al usuario y su rol.

---

# Redis y caché académica

Redis se incorpora como infraestructura de caché.

Puerto local:

```txt
6379
```

El `academico-service` habilita Spring Cache y configura Redis como parte de su entorno.

Actualmente se utiliza caché para la consulta de cursos.

```txt
CursoService.listarTodos()
        │
        ▼
     @Cacheable
        │
        ▼
    cache "cursos"
```

Cuando se registra un curso, la caché correspondiente se invalida mediante `@CacheEvict`.

Este flujo evita reutilizar información desactualizada después de una operación de escritura.

---

# RabbitMQ y procesamiento asíncrono

RabbitMQ se utiliza para procesamiento de mensajes en segundo plano.

Puertos:

| Puerto | Uso |
|---|---|
| `5672` | Comunicación AMQP |
| `15672` | Consola de administración |

Al registrar un nuevo aviso institucional, `comunicacion-service` persiste la información y publica un mensaje en:

```txt
notificaciones_queue
```

Flujo:

```txt
AvisoService
     │
     │ guarda aviso
     ▼
   MySQL
     │
     │ publica evento
     ▼
RabbitMQ
     │
     │ notificaciones_queue
     ▼
NotificacionListener
     │
     ▼
Procesamiento asíncrono
```

La publicación utiliza `RabbitTemplate` y el consumidor utiliza `@RabbitListener`.

---

# Swagger y OpenAPI

Los servicios REST incorporan documentación de APIs mediante Springdoc OpenAPI y Swagger UI.

La configuración OpenAPI contempla autenticación HTTP Bearer con JWT.

Esquema utilizado:

```txt
bearerAuth
    │
    ├── type: HTTP
    ├── scheme: bearer
    └── bearerFormat: JWT
```

Los servicios permiten las rutas de documentación:

```txt
/v3/api-docs/**
/swagger-ui/**
/swagger-ui.html
```

Los controladores utilizan anotaciones OpenAPI como:

```java
@Tag
@Operation
@Schema
```

para complementar la descripción de recursos y operaciones.

---

# Persistencia con MySQL

MySQL 8.0 se utiliza como motor de persistencia relacional.

Puerto local:

```txt
3306
```

Los servicios que requieren persistencia utilizan Spring Data JPA y Hibernate.

La configuración de conexión se distribuye mediante Config Server.

Ejemplo conceptual:

```txt
Controller
    │
    ▼
Service
    │
    ▼
Repository
    │
    ▼
Spring Data JPA
    │
    ▼
MySQL
```

---

# Docker e infraestructura

El ecosistema se ejecuta mediante Docker Compose.

El archivo principal es:

```txt
docker-compose.yml
```

La composición incluye:

```txt
mysql-db
config-server
eureka-server
api-gateway
auth-service
usuario-service
academico-service
estudiante-service
asistencia-service
evaluacion-service
anotacion-service
comunicacion-service
notificacion-service
reporte-service
redis-cache
rabbitmq-broker
```

Los contenedores comparten la red:

```txt
microservices-net
```

Docker Compose administra la construcción y ejecución coordinada de los servicios.

---

# Puertos de infraestructura

| Componente | Puerto |
|---|---|
| API Gateway | `8080` |
| Eureka Server | `8761` |
| Config Server | `8888` |
| MySQL | `3306` |
| Redis | `6379` |
| RabbitMQ AMQP | `5672` |
| RabbitMQ Management | `15672` |

Los microservicios funcionales se comunican dentro de la red Docker y el tráfico de la aplicación se canaliza mediante el API Gateway.

---

# Endpoints base

Las solicitudes de la aplicación deben utilizar el Gateway:

```txt
http://localhost:8080
```

| Dominio | Endpoint base |
|---|---|
| Autenticación | `/api/auth` |
| Usuarios | `/api/usuarios` |
| Académico | `/api/academico` |
| Estudiantes | `/api/estudiantes` |
| Asistencia | `/api/asistencia` |
| Evaluaciones | `/api/evaluaciones` |
| Anotaciones | `/api/anotaciones` |
| Comunicaciones | `/api/comunicaciones` |
| Notificaciones | `/api/notificaciones` |
| Reportes | `/api/reportes` |

Ejemplo de login:

```http
POST http://localhost:8080/api/auth/login
```

---

# Ejecución del proyecto

## 1. Clonar el repositorio

```bash
git clone https://github.com/Mayckol2005/FullStack-III.git
```

## 2. Ingresar al repositorio

```bash
cd FullStack-III
```

## 3. Construir y levantar el ecosistema

```bash
docker compose up -d --build
```

## 4. Revisar el estado de los contenedores

```bash
docker compose ps
```

## 5. Consultar logs

```bash
docker compose logs --tail=100
```

Para revisar un servicio específico:

```bash
docker compose logs --tail=100 academico-service
```

---

# Detener el ecosistema

Para detener los contenedores:

```bash
docker compose down
```

Para volver a construir servicios después de modificar el código:

```bash
docker compose up -d --build
```

---

# Testing y calidad de software

Los servicios incorporan pruebas automatizadas utilizando JUnit 5, Mockito y Spring Boot Test.

La suite se organiza por responsabilidad.

Ejemplos:

```txt
src/test/java/
├── controller/
├── service/
├── exception/
└── resource/
```

Las pruebas contemplan, según el servicio:

- Lógica de negocio.
- Controladores REST.
- Respuestas HTTP.
- Manejo de excepciones.
- Persistencia simulada mediante mocks.
- Clientes y dependencias externas simuladas.
- Contexto de aplicación.

---

# Cobertura con JaCoCo

Los servicios incorporan JaCoCo dentro de su configuración Maven para generar reportes de cobertura durante la ejecución de pruebas.

En un microservicio Maven se pueden ejecutar las pruebas con:

```bash
mvn test
```

El reporte generado por JaCoCo se encuentra normalmente en:

```txt
target/site/jacoco/index.html
```

La cobertura debe interpretarse de acuerdo con las exclusiones configuradas para cada módulo.

---

# Validación funcional

Además de las pruebas unitarias, el ecosistema fue validado mediante flujos integrados a través del API Gateway.

Entre los flujos verificados se encuentran:

- Autenticación y generación de JWT.
- Consulta de asignaciones académicas.
- Registro y recuperación de evaluaciones.
- Registro y recuperación de asistencia.
- Gestión e historial de anotaciones.
- Comunicación entre servicios mediante OpenFeign.
- Persistencia en MySQL.
- Integración del frontend con los servicios backend.

---

# Estrategia de branching

El equipo utiliza **GitHub Flow**.

| Rama | Propósito |
|---|---|
| `main` | Rama estable e integrada |
| `feat/*` | Desarrollo de funcionalidades |
| `fix/*` | Corrección de errores |
| `docs/*` | Cambios de documentación |

Flujo de integración:

```txt
main
  │
  └── rama de trabajo
          │
          ├── commits
          ├── push
          └── Pull Request
                    │
                    ▼
                   main
```

Los cambios son integrados mediante Pull Requests.

---

# Estado actual

- [x] Arquitectura distribuida.
- [x] API Gateway.
- [x] Service Discovery con Eureka.
- [x] Configuración centralizada.
- [x] Autenticación JWT.
- [x] Control de acceso por roles.
- [x] Persistencia con MySQL.
- [x] Comunicación síncrona mediante OpenFeign.
- [x] Caché con Redis.
- [x] Mensajería con RabbitMQ.
- [x] OpenAPI y Swagger.
- [x] Backend For Frontend.
- [x] Servicios contenerizados.
- [x] Orquestación mediante Docker Compose.
- [x] Pruebas automatizadas.
- [x] Cobertura instrumentada mediante JaCoCo.
- [x] Integración funcional con el frontend.

---

# Repositorios

## Backend

https://github.com/Mayckol2005/FullStack-III

## Frontend

https://github.com/Mayckol2005/FullStack-III-Frontend

---

# Asignatura

**Desarrollo Fullstack III**

Profesor: **Marcelo Crisostomo**