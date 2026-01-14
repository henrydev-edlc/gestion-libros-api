package com.biblioteca.repository;

import com.biblioteca.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio para acceso a datos Book
public interface BookRepository extends JpaRepository<Book, Long> {

    // Buscar libro por titulo o autor con paginacion
    Page<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
            String title,
            String author,
            Pageable pageable
    );
}
