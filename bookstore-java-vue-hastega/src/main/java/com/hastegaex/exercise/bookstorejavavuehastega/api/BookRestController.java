package com.hastegaex.exercise.bookstorejavavuehastega.api;

import com.hastegaex.exercise.bookstorejavavuehastega.model.Book;
import com.hastegaex.exercise.bookstorejavavuehastega.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin
public class BookRestController {
    @Autowired
    private BookService bookService;

    // Rotta "/api/v1/books/user/id" <---(dinamico) (GET)
    @GetMapping("/user/{id}")
    public List<Book> index(@PathVariable Integer id) {
        // Ritorno lista di libri per utente
        return bookService.getBookListByUserId(id);
    }

    // Rotta "/api/v1/books/id/show" <---(id dinamico) (GET)
    @GetMapping("/{id}/show")
    public Book show(@PathVariable Integer id) {
        // Ritorno libro
        return bookService.getBookListById(id);
    }
}
