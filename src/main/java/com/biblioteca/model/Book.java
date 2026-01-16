package com.biblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

// Clase que representa la entidad Book en la bd
@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // No vacío, solo letras y espacios
    @NotBlank(message = "El título es obligatorio")
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9 \\-.:]+$",
            message = "El titulo solo debe contener letras, números, espacios, guiones, puntos y dos puntos"
    )
    private String title;


    @NotBlank(message = "El autor es obligatorio")
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ -]+$",
            message = "El autor solo debe contener letras, espacios y guiones normales"
    )
    private String author;

    // Solo numeros y guion normal (-), sin espacios ni letras
    @NotBlank(message = "El ISBN es obligatorio")
    @Pattern(
            regexp = "^[0-9-]+$",
            message = "El isbn solo debe contener numeros y guiones"
    )
    @Column(unique = true)
    private String isbn;

    @Min(value = 1000, message = "El año de publicacion debe ser mayor a 1000")
    private Integer publishYear;

    @NotBlank(message = "El genero es requerido")
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El genero debe contener solo letras y espacios"
    )
    private String genre;

    // Solo numeros enteros positivos
    @NotNull(message = "La cantidad es requerida")
    @Min(value=0, message = "La cantidad no puede ser negativa")
    private Integer quantity;
}