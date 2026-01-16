# 📚 Gestión de Libros API

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Docker](https://img.shields.io/badge/Docker-Published-blue)

Una API REST profesional construida con **Spring Boot** para la gestión de inventario de una biblioteca. Esta aplicación implementa un buscador inteligente, validaciones robustas y está completamente contenedrizada para un despliegue inmediato.

## 🐳 Imagen Oficial en Docker Hub
La imagen está publicada y lista para ser utilizada en entornos de prueba o producción:
👉 [henrydev2026/biblioteca-api](https://hub.docker.com/r/henrydev2026/biblioteca-api)

## 🚀 Características Principales (v2.0)

* **Búsqueda Unificada**: Implementación de un endpoint global `/api/books/search` que filtra simultáneamente por título o autor.
* **Validaciones**: 
    * Permite títulos técnicos con números y puntos (ej. *Spring Boot v3.0*).
    * Bloquea búsquedas compuestas únicamente por números para garantizar la integridad de los resultados.
* **Persistencia Garantizada**: Configuración de volúmenes de Docker para asegurar que los libros registrados no se pierdan al reiniciar los servicios.
* **Calidad de Código**: Suite de pruebas unitarias (`JUnit 5`) actualizadas para validar la lógica del buscador.

## 🛠️ Instalación y Despliegue

### Requisitos previos
* Docker y Docker Compose instalados.

### Despliegue rápido con Docker Compose
El archivo `docker-compose.yml` administra automáticamente la API y la base de datos MySQL.

1.  Copia tu archivo `docker-compose.yml` en una carpeta local.
2.  Ejecuta el siguiente comando en la terminal:

```bash
sudo docker-compose up -d
