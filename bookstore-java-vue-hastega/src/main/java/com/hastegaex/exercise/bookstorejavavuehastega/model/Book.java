package com.hastegaex.exercise.bookstorejavavuehastega.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il titolo non puo' essere nullo")
    @Size(max = 255, message = "Il titolo non può essere più di 255 caratteri")
    private String title;

    @NotBlank(message = "L'autore non puo' essere nullo")
    @Size(max = 255, message = "L'autore non può essere più di 255 caratteri")
    private String author;

    @NotBlank(message = "L'ISBN non puo' essere nullo")
    @Size(max = 13, message = "L'ISBN non può essere più di 13 caratteri")
    @Column(length = 13, nullable = false, unique = true)
    private String isbn;

    private String createdAt;

    @Column(nullable = true)
    private String deletedAt;

    @Column(columnDefinition = "TEXT")
    private String plot;

    @Min(0)
    private Integer numberOfCompleteReadings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Integer getNumberOfCompleteReadings() {
        return numberOfCompleteReadings;
    }

    public void setNumberOfCompleteReadings(Integer numberOfCompleteReadings) {
        this.numberOfCompleteReadings = numberOfCompleteReadings;
    }
}
