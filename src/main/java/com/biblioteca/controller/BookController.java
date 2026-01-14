package com.biblioteca.controller;

import com.biblioteca.model.Book;
import com.biblioteca.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    // GET /api/books/ -> Lista libros
    @GetMapping
    public Page<Book> list(Pageable pageable) {
        return bookService.getAll(pageable);
    }

    // GET /api/books/ -> Obtiene libro por id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getOne(@PathVariable Long id) {
        return bookService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/books/ -> Crear un nuevo libro
    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    // PUT /api/books/ -> Actualizar un libro existente
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody Book book) {
        return bookService.getById(id)
                .map(existingBook -> {
                    book.setId(existingBook.getId());
                    return ResponseEntity.ok(bookService.save(book));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/books/ -> Eliminar un libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/author")
    public Page<Book> searchByAuthor(
        @RequestParam
        @Pattern(
                regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
                message = "El autor solo acepta texto, no se permiten números ni símbolos"
        )
        String author,
        Pageable pageable
    ){
        return bookService.search(author, pageable);
    }

    @GetMapping("/search/title")
    public Page<Book> searchByTitle(
            @RequestParam String title,
            Pageable pageable
    ){
        // si el titulo  es solo numeros
        if(title.matches("^\\d+$")){
            throw new IllegalArgumentException("El título debe contener texto y no puede estar compuesto únicamente por números.");
        }
        return bookService.search(title, pageable);
    }

}