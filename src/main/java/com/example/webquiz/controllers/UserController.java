package com.example.webquiz.controllers;

import com.example.webquiz.entities.User;
import com.example.webquiz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/api/register")
    public User createUser(@Valid @RequestBody User user) {
        if (!checkEmail(user.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The email must have a valid format (with @ and .)");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    private boolean checkEmail(String email) {
        String[] items = new String[]{"@", "."};
        boolean found = true;
        for (String item : items) {
            if (!email.contains(item)) {
                found = false;
                break;
            }
        }
        return found;
    }
}
