package com.hastegaex.exercise.bookstorejavavuehastega.api;

import com.hastegaex.exercise.bookstorejavavuehastega.model.Book;
import com.hastegaex.exercise.bookstorejavavuehastega.model.User;
import com.hastegaex.exercise.bookstorejavavuehastega.service.BookService;
import com.hastegaex.exercise.bookstorejavavuehastega.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin
public class BookRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    // Rotta "/api/v1/books/user/id" <---(dinamico) (GET)
    @GetMapping("user/{id}")
    public User index(@PathVariable Integer id) {
        // Ritorno lista degli utenti
        return userService.getBookListByUserId(id);
    }

    // Rotta "/api/v1/books/id" <---(dinamico) (GET)
    @GetMapping("/{id}")
    public Book findById(@PathVariable Integer id) {
        // Ritorno libro
        return bookService.getBookById(id);
    }
}
