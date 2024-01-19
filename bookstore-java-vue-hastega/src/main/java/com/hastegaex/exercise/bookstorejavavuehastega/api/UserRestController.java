package com.hastegaex.exercise.bookstorejavavuehastega.api;

import com.hastegaex.exercise.bookstorejavavuehastega.model.User;
import com.hastegaex.exercise.bookstorejavavuehastega.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserRestController {
    @Autowired
    private UserService userService;

    // Rotta "/api/v1/users" (GET)
    @GetMapping
    public List<User> index() {
        // Ritorno lista di utenti
        return userService.getUserList();
    }
}
