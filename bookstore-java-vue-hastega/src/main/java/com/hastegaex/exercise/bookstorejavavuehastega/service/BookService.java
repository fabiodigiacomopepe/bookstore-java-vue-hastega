package com.hastegaex.exercise.bookstorejavavuehastega.service;

import com.hastegaex.exercise.bookstorejavavuehastega.model.Book;
import com.hastegaex.exercise.bookstorejavavuehastega.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // Istanzio automaticamente BookRepository tramite Autowired
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBookListByUserId(Integer id) {
        return bookRepository.findAllByUserIdWhereDeletedAtIsNotNull(id);
    }

    public Book getBookListById(Integer id) {
        return bookRepository.findAllById(id);
    }

}
