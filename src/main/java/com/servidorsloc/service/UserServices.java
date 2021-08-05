package com.servidorsloc.service;

import com.servidorsloc.model.User;
import com.servidorsloc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return (List<User>) this.userRepository.findAll();
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User update(User user) {
        //buscando o user no banco
        Optional<User> u = this.userRepository.findById(user.getId());
        User userBanco = u.get();
        //modificando o user vindo do banco com os novos dados
        userBanco.setUsername(user.getUsername());
        userBanco.setPassword(user.getPassword());
        userBanco.setAdmin(user.isAdmin());

        return this.userRepository.save(userBanco);
    }

    public void delete(long id) {
        this.userRepository.deleteById(id);
    }
}
