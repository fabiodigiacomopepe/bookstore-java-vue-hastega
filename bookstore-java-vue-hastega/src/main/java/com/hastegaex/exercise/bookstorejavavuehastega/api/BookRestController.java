package com.hastegaex.exercise.bookstorejavavuehastega.api;

import com.hastegaex.exercise.bookstorejavavuehastega.exceptions.UserNotFoundException;
import com.hastegaex.exercise.bookstorejavavuehastega.model.Book;
import com.hastegaex.exercise.bookstorejavavuehastega.model.User;
import com.hastegaex.exercise.bookstorejavavuehastega.service.BookService;
import com.hastegaex.exercise.bookstorejavavuehastega.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin
public class BookRestController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

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
        return bookService.getBookById(id);
    }

    // Rotta "/api/v1/books/id/edit" <---(id dinamico) (POST)
    @PostMapping("/{id}/edit")
    public ResponseEntity<String> editBook(@RequestBody Book formBook) {
        Book savedBook = bookService.editBook(formBook);
        return ResponseEntity.ok("Book saved.");
    }

    // Rotta "/api/v1/books/id/delete" <---(id dinamico) (POST)
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id, @RequestBody String formData) {
        Book deletedBook = bookService.deleteBook(id, formData);
        return ResponseEntity.ok("Book deleted.");
    }

    // Rotta "/api/v1/books/id/delete" <---(id dinamico) (POST)
    @PostMapping("/user/{id}/create")
    public Book createBook(@PathVariable Integer id, @RequestBody Book formBook) throws UserNotFoundException {
        Optional<User> userBook = userService.getUserById(id);
        Book createBook = null;
        if (userBook.isPresent()) {
            createBook = bookService.createBook(userBook, formBook);
        }
        return createBook;
    }
}
