# 📚 Colegio Bernardo O’Higgins — Plataforma de Gestión Escolar Distribuida

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge\&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-brightgreen?style=for-the-badge\&logo=spring-boot)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue?style=for-the-badge\&logo=docker)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge\&logo=mysql)

Sistema de gestión académica desarrollado bajo una arquitectura de microservicios utilizando Spring Boot, Spring Cloud y Docker.

La plataforma busca modernizar y centralizar los procesos académicos, administrativos y comunicacionales del Colegio Bernardo O’Higgins mediante un ecosistema distribuido, escalable y seguro.

---

# 👥 Integrantes

* Martín Baza
* Mayckol Mardones
* Francisco Vera

---

# 🎯 Objetivo del Proyecto

Desarrollar una plataforma web integral basada en microservicios que permita gestionar:

* Información académica
* Matrículas
* Asistencia
* Evaluaciones
* Anotaciones
* Comunicaciones institucionales
* Notificaciones automáticas
* Reportes académicos

Todo esto mediante una arquitectura desacoplada, escalable y preparada para ambientes distribuidos.

---

# 🏗️ Arquitectura del Sistema

El sistema fue diseñado utilizando una arquitectura basada en microservicios, implementando componentes especializados e independientes.

## Componentes principales

* API Gateway
* Eureka Server
* Config Server
* Auth Service (JWT)
* Backend For Frontend (BFF)
* Microservicios funcionales independientes
* Configuración centralizada
* Comunicación inter-servicio mediante OpenFeign

---

# 📁 Estructura del Proyecto

```txt
FULLSTACK-III/
│
├── academico-service
├── anotacion-service
├── api-gateway
├── asistencia-service
├── auth-service
├── comunicacion-service
├── config-repo
├── config-server
├── estudiante-service
├── eureka-server
├── evaluacion-service
├── notificacion-service
├── reporte-service
├── usuario-service
│
├── docker-compose.yml
└── README.md
```

---

# 🔧 Microservicios Implementados

| Microservicio        | Responsabilidad                    |
| -------------------- | ---------------------------------- |
| auth-service         | Autenticación y generación de JWT  |
| usuario-service      | Gestión de usuarios y roles        |
| estudiante-service   | Gestión de estudiantes             |
| academico-service    | Gestión académica                  |
| evaluacion-service   | Registro y cálculo de evaluaciones |
| asistencia-service   | Control de asistencia              |
| anotacion-service    | Gestión de anotaciones             |
| comunicacion-service | Comunicaciones institucionales     |
| notificacion-service | Envío de notificaciones            |
| reporte-service      | Backend For Frontend (BFF)         |
| api-gateway          | Punto de entrada centralizado      |
| eureka-server        | Service Discovery                  |
| config-server        | Configuración centralizada         |

---

# 🧱 Arquitectura Interna de los Microservicios

Cada microservicio fue desarrollado siguiendo una estructura desacoplada basada en capas, permitiendo mantenibilidad, escalabilidad y separación clara de responsabilidades.

## Capas utilizadas

| Capa       | Responsabilidad                                 |
| ---------- | ----------------------------------------------- |
| Controller | Exposición de endpoints REST                    |
| Service    | Lógica de negocio                               |
| Repository | Acceso a datos mediante Spring Data JPA         |
| Entity     | Modelado de datos persistentes                  |
| DTO        | Transferencia segura de información             |
| Config     | Configuración de seguridad y componentes        |
| Exception  | Manejo global de excepciones                    |
| Client     | Comunicación entre servicios mediante OpenFeign |

---

# 🚀 Stack Tecnológico

## Backend

* Java 21
* Spring Boot 3.2.5
* Spring Cloud 2023.0.1
* Spring Security
* Spring Cloud Gateway
* Spring Cloud Config
* Eureka Netflix
* OpenFeign
* JWT (JSON Web Token)
* Maven

## Base de Datos

* MySQL 8.0

## DevOps e Infraestructura

* Docker
* Docker Compose

## Testing

* JUnit 5
* Mockito
* Spring Boot Test

## Control de Versiones

* Git
* GitHub

---

# 🧩 Patrones de Diseño y Arquitectura

El proyecto implementa múltiples patrones arquitectónicos y de diseño enfocados en escalabilidad, mantenibilidad y desacoplamiento.

| Patrón                     | Implementación                    |
| -------------------------- | --------------------------------- |
| Microservices Architecture | Separación funcional por dominios |
| Backend For Frontend (BFF) | reporte-service                   |
| API Gateway                | api-gateway                       |
| Repository Pattern         | Spring Data JPA                   |
| MVC                        | Controller + Service + Repository |
| DTO Pattern                | Transferencia segura de datos     |
| Dependency Injection       | IoC Container de Spring           |
| Service Discovery          | Eureka Server                     |
| Centralized Configuration  | Spring Cloud Config               |
| Global Exception Handler   | GlobalExceptionHandler.java       |
| OpenFeign Client           | Comunicación entre servicios      |

---

# 🔗 Comunicación Entre Microservicios

La comunicación entre servicios se implementó utilizando OpenFeign mediante clientes desacoplados.

## Beneficios de OpenFeign

* Desacoplamiento
* Comunicación declarativa
* Integración simple con Eureka
* Mantenibilidad
* Balanceo de carga automático

---

# ⚙️ Configuración Centralizada

El ecosistema implementa Spring Cloud Config para centralizar la configuración de todos los microservicios.

## Componentes

### Config Server

Encargado de distribuir configuraciones dinámicamente a los servicios a través del puerto `8888`.

### Config Repo

Repositorio centralizado que contiene los archivos YAML (`academico-service.yml`, `auth-service.yml`, etc.) con los parámetros específicos de cada servicio.

---

# 🔐 Seguridad

La plataforma implementa autenticación basada en JWT (JSON Web Token).

## Características

* Generación de tokens mediante `auth-service`
* Validación de seguridad centralizada desde `api-gateway`
* Seguridad stateless
* Control de acceso basado en roles
* Comunicación interna segura entre servicios protegidos

---

# 🐳 Docker e Infraestructura Distribuida

El proyecto utiliza Docker para facilitar el despliegue, la orquestación de la red interna y la portabilidad absoluta de los servicios.

## docker-compose.yml (Ejemplo)

```yaml
services:
  mysql-db:
    image: mysql:8.0
    container_name: mysql-db
    environment:
      - MYSQL_ALLOW_EMPTY_PASSWORD=yes
      - MYSQL_DATABASE=colegio_db
    ports:
      - "3306:3306"
    networks:
      - microservices-net

  eureka-server:
    build: ./eureka-server
    container_name: eureka-server
    ports:
      - "8761:8761"
    networks:
      - microservices-net

networks:
  microservices-net:
    driver: bridge
```

---

# ▶️ Ejecución del Proyecto

La arquitectura es 100% inmutable y portable. Docker Compose se encarga de:

* Orquestación de contenedores
* Creación de red interna
* Inicialización de servicios
* Service Discovery
* Configuración distribuida

---

## 1️⃣ Clonar repositorio

```bash
git clone https://github.com/Mayckol2005/FullStack-III.git
cd FullStack-III
```

---

## 2️⃣ Levantar la infraestructura completa

```bash
docker-compose up -d --build
```

> ⚠️ Nota: En un inicio en frío, los servicios pueden tardar entre 60 y 90 segundos en registrarse completamente en Eureka y quedar disponibles a través del API Gateway.

---

# 🌐 Endpoints Base

> ⚠️ IMPORTANTE: Todas las peticiones deben realizarse a través del API Gateway (`http://localhost:8080`).

| Servicio       | Endpoint Base                              |
| -------------- | ------------------------------------------ |
| Auth (Login)   | `http://localhost:8080/api/auth`           |
| Usuarios       | `http://localhost:8080/api/usuarios`       |
| Estudiantes    | `http://localhost:8080/api/estudiantes`    |
| Evaluaciones   | `http://localhost:8080/api/evaluaciones`   |
| Reportes (BFF) | `http://localhost:8080/api/reportes`       |
| Asistencia     | `http://localhost:8080/api/asistencia`     |
| Comunicación   | `http://localhost:8080/api/comunicaciones` |
| Anotaciones    | `http://localhost:8080/api/anotaciones`    |

---

# 🧪 Testing y Calidad de Software

El proyecto implementa pruebas automatizadas utilizando JUnit 5 y Mockito para garantizar estabilidad y confiabilidad.

## Objetivos de las pruebas

* Validar lógica de negocio
* Verificar cálculo de métricas
* Simular respuestas externas con mocks
* Asegurar estabilidad de respuestas agregadas
* Validar integración entre capas

---

# 🧪 Testing y Calidad de Software

El proyecto implementa pruebas automatizadas para garantizar la estabilidad y confiabilidad de todo el ecosistema.

## 📊 Cobertura de Código (Code Coverage)
Se utilizó **JaCoCo** para el ecosistema de microservicios y **v8/Vitest** para la interfaz de usuario, superando el estándar mínimo del 90%:
* **Backend (Microservicios):** 100% de cobertura global (Instructions, Branches, Lines, Methods).
* **Frontend (React):** 96.24% de cobertura en líneas de código.

---

# 🌱 Estrategia de Branching

Se implementó la metodología GitHub Flow con la siguiente estructura:

| Rama   | Propósito                            |
| ------ | ------------------------------------ |
| main   | Rama de producción estable           |
| feat/* | Desarrollo de nuevas funcionalidades |

La integración se realiza mediante Pull Requests y resolución manual de conflictos.

---

# 📌 Estado del Proyecto

Proyecto académico correspondiente a la asignatura **Desarrollo Fullstack III**.

## Estado actual

* [x] Arquitectura distribuida 100% funcional
* [x] 13 microservicios operativos bajo Docker
* [x] Integración dinámica mediante Eureka Server
* [x] API Gateway enrutando el tráfico
* [x] Seguridad JWT implementada y estabilizada
* [x] Configuración centralizada vía Config Server
* [x] Pruebas unitarias funcionales
* [x] Comunicación inter-servicio mediante OpenFeign
* [x] Bypass de Jackson configurado

---

# 📖 Conclusión

La plataforma Colegio Bernardo O’Higgins representa una solución moderna basada en arquitectura distribuida, aplicando principios de escalabilidad, desacoplamiento y mantenibilidad mediante tecnologías actuales del ecosistema Spring y contenedores Docker.

El proyecto demuestra la implementación práctica de microservicios empresariales, seguridad JWT, descubrimiento de servicios, configuración centralizada y despliegue automatizado en ambientes distribuidos.
