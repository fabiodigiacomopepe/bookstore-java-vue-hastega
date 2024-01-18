package com.hastegaex.exercise.bookstorejavavuehastega.service;

import com.hastegaex.exercise.bookstorejavavuehastega.model.User;
import com.hastegaex.exercise.bookstorejavavuehastega.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    // Istanzio automaticamente UserRepository tramite Autowired
    @Autowired
    private UserRepository userRepository;

    public List<User> getUserList() {
        // Ritorno lista completa di utenti
        return userRepository.findAll();
    }
}
