package com.biblioteca.service;

import com.biblioteca.model.Book;
import com.biblioteca.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
// Logica de negocio
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Obtiene todos los libros con paginacion
    public Page<Book> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    // Obtiene un libro por id
    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    // Guarda o actualiza un libro
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    // Elimina un libro por id
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    // Busca libros por titutlo o autor
    public Page<Book> search(String query, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query, pageable);
    }
}