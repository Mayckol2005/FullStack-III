# 📚 Colegio Bernardo O’Higgins — Plataforma de Gestión Escolar Distribuida

Sistema de gestión académica desarrollado bajo una arquitectura de microservicios utilizando Spring Boot, Spring Cloud y Docker.  
La plataforma busca modernizar y centralizar los procesos académicos, administrativos y comunicacionales del Colegio Bernardo O’Higgins mediante un ecosistema distribuido, escalable y seguro.

---

# 👥 Integrantes

- Francisco Vera
- Mayckol Mardones
- Martín Baza

---

# Objetivo del Proyecto

Desarrollar una plataforma web integral basada en microservicios que permita gestionar:

- información académica
- matrículas
- asistencia
- evaluaciones
- anotaciones
- comunicaciones
- reportes institucionales

Todo esto mediante una arquitectura desacoplada, escalable y preparada para ambientes distribuidos.

---

# Arquitectura del Sistema

El sistema fue diseñado utilizando una arquitectura basada en microservicios, implementando componentes especializados y desacoplados.

## Componentes principales

- API Gateway
- Eureka Server
- Config Server
- Auth Service (JWT)
- Backend For Frontend (BFF)
- Microservicios funcionales independientes

---
# Estructura del Proyecto

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

# Microservicios Implementados

| Microservicio | Responsabilidad |
|---|---|
| auth-service | Autenticación y generación de JWT |
| usuario-service | Gestión de usuarios y roles |
| estudiante-service | Gestión de estudiantes |
| academico-service | Gestión académica |
| evaluacion-service | Registro y cálculo de evaluaciones |
| asistencia-service | Control de asistencia |
| anotacion-service | Gestión de anotaciones |
| comunicacion-service | Comunicaciones institucionales |
| notificacion-service | Envío de notificaciones |
| reporte-service | Backend For Frontend (BFF) |
| api-gateway | Punto de entrada centralizado |
| eureka-server | Service Discovery |
| config-server | Configuración centralizada |

---
