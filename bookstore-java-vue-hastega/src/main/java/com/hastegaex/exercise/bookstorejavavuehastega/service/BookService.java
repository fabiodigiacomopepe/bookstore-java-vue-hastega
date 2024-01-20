package com.hastegaex.exercise.bookstorejavavuehastega.service;

import com.hastegaex.exercise.bookstorejavavuehastega.exceptions.BookNotFoundException;
import com.hastegaex.exercise.bookstorejavavuehastega.model.Book;
import com.hastegaex.exercise.bookstorejavavuehastega.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class BookService {
    // Istanzio automaticamente BookRepository tramite Autowired
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBookListByUserId(Integer id) {
        return bookRepository.findAllByUserIdWhereDeletedAtIsNotNull(id);
    }

    public Book getBookById(Integer id) {
        return bookRepository.findAllById(id);
    }

    public Book editBook(Book book) throws BookNotFoundException {
        // Provo a prendere book in base a id
        Book bookToEdit = getBookById(book.getId());
        // Valorizzo con i setter i vari parametri passando quelli ricevuti dal form
        bookToEdit.setAuthor(book.getAuthor());
        bookToEdit.setCreatedAt(book.getCreatedAt());
        bookToEdit.setDeletedAt(book.getDeletedAt());
        bookToEdit.setId(book.getId());
        bookToEdit.setIsbn(book.getIsbn());
        bookToEdit.setNumberOfCompleteReadings(book.getNumberOfCompleteReadings());
        bookToEdit.setPlot(book.getPlot());
        bookToEdit.setTitle(book.getTitle());

        // Salvo il book
        // Metodo .save salva ciò che riceve. Se i campi nel form mancano, li lascia vuoti (non si comporta come update)
        return bookRepository.save(bookToEdit);
    }

    public Book deleteBook(Integer id, String formData) throws BookNotFoundException {
        // Provo a prendere book in base a id
        Book bookToDelete = getBookById(id);

        // Converto stringa eliminado caratteri speciali
        String dataDecoded = URLDecoder.decode(formData, StandardCharsets.UTF_8);
        String dataDecodedFinal = dataDecoded.replace("=", "");

        // Valorizzo con i setter i vari parametri passando quelli ricevuti dal form
        bookToDelete.setDeletedAt(dataDecodedFinal);
        // Salvo il book
        // Metodo .save salva ciò che riceve. Se i campi nel form mancano, li lascia vuoti (non si comporta come update)
        return bookRepository.save(bookToDelete);
    }
}
