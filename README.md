# 📊 Countify — REST API de Finanzas Personales

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openapiinitiative&logoColor=white" alt="Java 21" />
  <img src="https://img.shields.io/badge/Spring_Boot-4.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white" alt="Spring Security JWT" />
  <img src="https://img.shields.io/badge/Database-MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />
</p>

<p align="center">
  <b>Countify</b> es actualmente un proyecto personal de API RESTful moderna, escalable y robusta diseñada para la gestión integral de finanzas personales. Pretende permitir a los usuarios llevar un control estricto de sus transacciones, personalizar sus categorías de gasto con límites de presupuesto y analizar sus hábitos financieros mediante una arquitectura segura y desacoplada.
</p>

---

## 🚀 Características Principales

- 🔐 **Autenticación Stateless (JWT):** Registro e inicio de sesión seguro mediante JSON Web Tokens y cifrado de contraseñas con BCrypt.
- 🏷️ **Categorías:** 
  - Categorías globales por defecto del sistema (*Comida, Transporte, Vivienda...*).
  - Categorías personalizadas por usuario con códigos de color HEX.
- 🎯 **Límites de gasto (`spendingLimit`):** Posibilidad de establecer límites de gasto definidos en función de criterios temporales y de categorías.
- 💳 **Gestión de Transacciones:** Registro estructurado de ingresos y gastos con trazabilidad por fecha y categoría.
- 📊 **Visualización de datos:** Inclusión de un dashboard con diferentes gráficas que permitan visualizar fácilmente el estado de sus finanzas al usuario.
- 🛡️ **Validación Estricta:** Verificación de datos de entrada en la capa DTO para asegurar la integridad de las peticiones.
- ⚡ **Control de Excepciones Global:** Respuestas de error estandarizadas para fallos.

---

## 🛠️ Tecnologías Utilizadas

| Categoría | Tecnología |
| :--- | :--- |
| **Lenguaje** | Java 21 |
| **Framework Principal** | Spring Boot |
| **Seguridad** | Spring Security, JJWT (Java JWT) |
| **Persistencia & ORM** | Spring Data JPA, Hibernate |
| **Base de Datos** | MySQL |
| **Herramientas & Utilidades** | Lombok, Jakarta Validation, Maven |

---

## 🏗 Arquitectura del Proyecto

El proyecto sigue una arquitectura en capas desacoplada (*Layered Architecture*), manteniendo una estricta separación entre la transferencia de datos (DTOs) y el dominio de base de datos (Entidades JPA):

```text
com.amsg.countify
 ├── controllers      # Endpoints RESTful y manejo de peticiones HTTP
 ├── dtos                  # Data Transfer Objects (Java Records) y Validaciones
 ├── entities            # Modelos de dominio / Tablas de la base de datos
 ├── repositories   # Interfaces de acceso a datos con Spring Data JPA
 ├── security           # Configuración de Seguridad (JWT, CORS, BCrypt)
 └── services          # Lógica de negocio e integración
```

---

## 💻 Configuración previa
- Java JDK 21 o superior instalado.
- Maven 3.8+
- Instancia MySQL en ejecución.
