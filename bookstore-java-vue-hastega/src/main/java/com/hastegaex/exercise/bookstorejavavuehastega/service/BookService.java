package com.hastegaex.exercise.bookstorejavavuehastega.service;

import com.hastegaex.exercise.bookstorejavavuehastega.exceptions.BookNotFoundException;
import com.hastegaex.exercise.bookstorejavavuehastega.model.Book;
import com.hastegaex.exercise.bookstorejavavuehastega.model.User;
import com.hastegaex.exercise.bookstorejavavuehastega.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class BookService {
    // Istanzio automaticamente BookRepository tramite Autowired
    @Autowired
    private BookRepository bookRepository;

    //    public List<Book> getBookListByUserId(Integer id) {
//        return bookRepository.findAllByUserIdWhereDeletedAtIsNotNull(id);
//    }
    public Page<Book> getBookListByUserId(Integer id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAllByUserIdAndDeletedAtIsNull(id, pageable);
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

    public Book createBook(Optional<User> userBook, Book formBook) {
        Book bookToSave = new Book();
        bookToSave.setId(null);
        bookToSave.setTitle(formBook.getTitle());
        bookToSave.setAuthor(formBook.getAuthor());
        bookToSave.setIsbn(formBook.getIsbn());
        bookToSave.setDeletedAt(formBook.getDeletedAt());
        bookToSave.setPlot(formBook.getPlot());
        bookToSave.setNumberOfCompleteReadings(formBook.getNumberOfCompleteReadings());
        bookToSave.setUser(userBook.get());

        // Converto stringa eliminado caratteri speciali
        String dataDecoded = URLDecoder.decode(formBook.getCreatedAt(), StandardCharsets.UTF_8);
        String dataDecodedFinal = dataDecoded.replace("=", "");

        // Valorizzo con i setter i vari parametri passando quelli ricevuti dal form
        bookToSave.setCreatedAt(dataDecodedFinal);

        return bookRepository.save(bookToSave);
    }
}
