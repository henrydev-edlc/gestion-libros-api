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

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Page<Book> list(Pageable pageable) {
        return bookService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getOne(@PathVariable Long id) {
        return bookService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody Book book) {
        return bookService.getById(id)
                .map(existingBook -> {
                    book.setId(existingBook.getId());
                    return ResponseEntity.ok(bookService.save(book));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public Page<Book> search(@RequestParam String query, Pageable pageable) {
        return bookService.search(query, pageable);
    }
}