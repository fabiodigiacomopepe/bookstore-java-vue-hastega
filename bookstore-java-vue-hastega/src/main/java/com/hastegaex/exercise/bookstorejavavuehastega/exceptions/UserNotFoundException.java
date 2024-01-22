package com.hastegaex.exercise.bookstorejavavuehastega.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}