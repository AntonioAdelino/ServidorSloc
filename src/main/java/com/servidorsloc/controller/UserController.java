package com.servidorsloc.controller;

import com.servidorsloc.model.User;
import com.servidorsloc.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> listAll() {
        return userServices.findAll();
    }

    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public User adicionar(@RequestBody User user) {
        return userServices.save(user);
    }

    @PutMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public User atualizar(@RequestBody User user) {
        return userServices.update(user);
    }


    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public void deletar(@RequestBody int id) {
        userServices.delete(Long.valueOf(id));
    }
}
