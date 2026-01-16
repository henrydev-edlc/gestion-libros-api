package com.biblioteca;

import com.biblioteca.controller.BookController;
import com.biblioteca.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BookControllerTest {

    @Autowired
    private BookController controller;

    @MockBean
    private BookService bookService;

    @Test
    void testBusquedaSoloNumerosNoValida() {
        // Debe fallar si el usuario solo ingresa números
        assertThrows(IllegalArgumentException.class, () -> {
            controller.search("2026", PageRequest.of(0, 10));
        });
    }

    @Test
    void testBusquedaConVersionValida(){
        // Valida que acepte títulos con puntos y versiones (la nueva mejora)
        assertDoesNotThrow(() -> {
            controller.search("Spring-Boot-v3.0", PageRequest.of(0, 10));
        });
    }

    @Test
    void testBusquedaTextoNormalValida(){
        // Valida que una búsqueda normal de autor o título pase sin problemas
        assertDoesNotThrow(() -> {
            controller.search("Gabriel Garcia Marquez", PageRequest.of(0, 10));
        });
    }
}