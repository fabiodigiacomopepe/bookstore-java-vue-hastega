package com.hastegaex.exercise.bookstorejavavuehastega.service;

import com.hastegaex.exercise.bookstorejavavuehastega.exceptions.UserNotFoundException;
import com.hastegaex.exercise.bookstorejavavuehastega.model.User;
import com.hastegaex.exercise.bookstorejavavuehastega.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    // Istanzio automaticamente UserRepository tramite Autowired
    @Autowired
    private UserRepository userRepository;

    public List<User> getUserList() {
        // Ritorno lista completa di utenti
        return userRepository.findAll();
    }

    public User getBookListByUserId(Integer id) throws UserNotFoundException {
        // Salvo il result in modo Optional perchè potrebbe non ritornare nulla
        Optional<User> result = userRepository.findById(id);
        // Se il risultato è presente
        if (result.isPresent()) {
            // Ritorno l'utente (result.get())
            return result.get();
        } else {
            // Altrimenti lancio eccezione
            throw new UserNotFoundException("User with ID " + id + ": Not Found");
        }
    }
}
