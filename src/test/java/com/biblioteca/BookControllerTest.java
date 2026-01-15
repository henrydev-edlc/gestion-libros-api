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
    void testTituloNoValido() {
        // Si el usuario ingresa "12345" manda un mensaje de error
        assertThrows(IllegalArgumentException.class, () -> {
            controller.searchByTitle("12345", PageRequest.of(0, 10));
        });
    }

    @Test
    void testTituloValido(){
        // valida que un titulo normal pase sin problemas
        assertDoesNotThrow(() -> {
            controller.searchByTitle("Cien años de soledad", PageRequest.of(0, 10));
        });
    }
}