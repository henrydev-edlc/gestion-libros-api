# 📚 Gestión de Libros API

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Docker](https://img.shields.io/badge/Docker-Published-blue)

Una API REST profesional construida con **Spring Boot** para la gestión de inventario de una biblioteca. Esta aplicación implementa un buscador inteligente, validaciones robustas y está completamente automatizada mediante contenedores para un despliegue inmediato.

## 🐳 Imagen Oficial en Docker Hub
La imagen está publicada y lista para ser utilizada:
👉 [henrydev2026/biblioteca-api](https://hub.docker.com/r/henrydev2026/biblioteca-api)

## 🚀 Características Principales (v2.0)

* **Búsqueda Unificada**: Endpoint global `/api/books/search` que filtra simultáneamente por título o autor.
* **Validaciones Inteligentes**: 
    * Permite títulos técnicos con números y puntos (ej. *Spring Boot v3.0*).
    * Bloquea búsquedas compuestas únicamente por números.
* **Persistencia de Datos**: Uso de volúmenes de Docker para asegurar que la base de datos MySQL conserve la información tras reinicios.
* **Calidad**: Suite de pruebas unitarias (`JUnit 5`) que validan la lógica de negocio y controladores.

## 🛠️ Instalación y Despliegue

### Requisitos previos
* Docker y Docker Compose instalados.

### Pasos para el despliegue
El archivo `docker-compose.yml` gestiona automáticamente la configuración de la red, la base de datos y la aplicación.

1. **Preparación**: Copia tu archivo `docker-compose.yml` en una carpeta local.
2. **Ejecución**: Ejecuta el siguiente comando en la terminal:
   ```bash
   sudo docker-compose up -d
