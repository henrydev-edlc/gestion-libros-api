package com.biblioteca;

import com.biblioteca.controller.BookController;
import com.biblioteca.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Esto le dice a Java que es una prueba de Spring
class BookControllerTest {

    @Autowired
    private BookController controller;

    @MockBean // Crea un "simulador" del servicio para que el controlador no de error
    private BookService bookService;

    @Test
    void testTituloSoloNumerosLanzaException() {
        // Tu lógica era correcta, solo faltaba el entorno de Spring
        assertThrows(IllegalArgumentException.class, () -> {
            controller.searchByTitle("123", PageRequest.of(0, 10));
        });
    }
}