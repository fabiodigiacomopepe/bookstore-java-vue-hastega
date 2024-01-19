package com.hastegaex.exercise.bookstorejavavuehastega.service;

import com.hastegaex.exercise.bookstorejavavuehastega.exceptions.BookNotFoundException;
import com.hastegaex.exercise.bookstorejavavuehastega.model.Book;
import com.hastegaex.exercise.bookstorejavavuehastega.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    // Istanzio automaticamente BookRepository tramite Autowired
    @Autowired
    private BookRepository bookRepository;

    public Book getBookById(Integer id) throws BookNotFoundException {
        // Salvo il result in modo Optional perchè potrebbe non ritornare nulla
        Optional<Book> result = bookRepository.findById(id);
        // Se il risultato è presente
        if (result.isPresent()) {
            // Ritorno il libro (result.get())
            return result.get();
        } else {
            // Altrimenti lancio eccezione
            throw new BookNotFoundException("Book with ID " + id + ": Not Found");
        }
    }
}
