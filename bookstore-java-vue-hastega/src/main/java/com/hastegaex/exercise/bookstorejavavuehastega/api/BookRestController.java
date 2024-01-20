package com.hastegaex.exercise.bookstorejavavuehastega.api;

import com.hastegaex.exercise.bookstorejavavuehastega.model.Book;
import com.hastegaex.exercise.bookstorejavavuehastega.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return bookService.getBookById(id);
    }

    // Rotta "/api/v1/books/id/edit" <---(id dinamico) (POST)
    @PostMapping("/{id}/edit")
    public ResponseEntity<String> editBook(@RequestBody Book formBook) {
        Book savedBook = bookService.editBook(formBook);
        return ResponseEntity.ok("Book saved.");
    }

    //    // Rotta "/api/v1/books/id/delete" <---(id dinamico) (POST)
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id, @RequestBody String formData) {
//        Book bookToDelete = bookService.getBookById(id);
        Book deletedBook = bookService.deleteBook(id, formData);
        return ResponseEntity.ok("Book deleted.");
    }
}
