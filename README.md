# 📚 Gestión de Libros API

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Docker](https://img.shields.io/badge/Docker-Published-blue)

Una API REST robusta construida con Spring Boot para la gestión de inventario de una biblioteca. Este proyecto implementa validaciones estrictas, sigue estándares profesionales y está listo para desplegarse mediante contenedores.

## 🐳 Imagen Oficial en Docker Hub
Puedes encontrar la imagen lista para producción aquí:
👉 [henrydev2026/biblioteca-api](https://hub.docker.com/r/henrydev2026/biblioteca-api)

## 🚀 Características

* **Validaciones Avanzadas**: Títulos y autores permiten letras, espacios y **guiones normales (-)**, rechazando números y caracteres especiales.
* **Búsqueda Flexible**: Filtros de búsqueda por título y autor con manejo de errores personalizado.
* **Infraestructura Moderna**: Orquestación completa con Docker y Docker Compose.
* **Documentación**: Swagger UI integrado para pruebas rápidas de los endpoints.
* **Calidad**: Suite de pruebas unitarias.

## 🛠️ Instalación y Despliegue

### Requisitos previos
* Docker y Docker Compose instalados.

### Despliegue rápido con Docker (Recomendado)
Para levantar la API y la base de datos MySQL automáticamente, ejecuta en la raíz del proyecto:
```bash
sudo docker-compose up -d
