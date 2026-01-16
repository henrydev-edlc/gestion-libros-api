package com.biblioteca.controller;

import com.biblioteca.model.Book;
import com.biblioteca.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    // GET /api/books/ -> Lista libros
    @Operation(
            summary = "Listar libros",
            parameters={
                    @Parameter(name = "page", description = "Numero de pagina actual"),
                    @Parameter(name = "size", description = "Libros por pagina"),
                    @Parameter(name = "sort", description = "Ordenar por (ej: title,desc)")
    }
    )
    @GetMapping
    public Page<Book> list(@ParameterObject Pageable pageable) {
        return bookService.getAll(pageable);
    }

    // GET /api/books/ -> Obtiene libro por id
    @Operation(summary = "Buscar libro por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getOne(@PathVariable Long id) {
        return bookService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Libro con ID " + id + " no existe"
                ));
    }

    // POST /api/books/ -> Crear un nuevo libro
    @Operation(summary = "Crear un nuevo libro")
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Book book) {
        Book savedBook = bookService.save(book);

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Operacion Exitosa: El libro con ID " + savedBook.getId() + " se creo correctamente");
        response.put("libro", savedBook);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // PUT /api/books/ -> Actualizar un libro existente
    @Operation(summary = "Actualizar un libro")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Valid @RequestBody Book book) {
        return bookService.getById(id)
                .map(existingBook -> {
                    book.setId(existingBook.getId());
                    Book updateBook = bookService.save(book);

                    Map<String,Object> response = new HashMap<>();
                    response.put("mensaje","Operacion exitosa: El libro con ID "+id+" ha sido actualizado");
                    return ResponseEntity.ok(response);
                })
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Libro con ID "+id+" no existe"
                ));
    }

    // DELETE /api/books/ -> Eliminar un libro
    @Operation(summary = "Eliminar un libro")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        if (!bookService.getById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Libro con ID "+id+" no existe");
        }
        bookService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje","Operacion Exitosa: El libro con ID "+id+" se elimino correctamente");
        return ResponseEntity.ok(response);
    }

    // SEARCH /api/books/search?query={término}
    @Operation(summary = "Búsqueda global por título o autor")
    @GetMapping("/search")
    public Page<Book> search(
            @RequestParam
            @Pattern(
                    regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9 \\-.:]+$",
                    message = "La búsqueda contiene caracteres no permitidos"
            )
            String query,
            @ParameterObject Pageable pageable
    ){
        // No permitir que la búsqueda sea solo números
        if(query.matches("^\\d+$")){
            throw new IllegalArgumentException("El término de búsqueda no puede ser solo números.");
        }

        return bookService.search(query, pageable);
    }

}