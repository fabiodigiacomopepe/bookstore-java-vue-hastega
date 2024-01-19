package com.hastegaex.exercise.bookstorejavavuehastega.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}