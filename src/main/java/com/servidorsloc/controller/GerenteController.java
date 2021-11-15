package com.servidorsloc.controller;

import java.util.List;

import com.servidorsloc.model.Login;
import com.servidorsloc.model.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.servidorsloc.service.GerenteServices;
import com.servidorsloc.model.Gerente;


@RestController
public class GerenteController {

    @Autowired
    private GerenteServices gerenteServices;

    @PostMapping("/gerentes/login")
    public Gerente login(@RequestBody Login login) {
        return gerenteServices.login(login.getEmail(), login.getSenha());
    }

    @GetMapping("/gerentes")
    public List<Gerente> listAll() {
        return gerenteServices.findAll();
    }

    @PostMapping("/gerentes")
    @ResponseStatus(HttpStatus.CREATED)
    public Gerente adicionar(@RequestBody Gerente gerente) {
        return gerenteServices.save(gerente);
    }

    @PutMapping("/gerentes")
    @ResponseStatus(HttpStatus.OK)
    public Gerente atualizar(@RequestBody Gerente gerente) {
        return gerenteServices.update(gerente);
    }

    @DeleteMapping("/gerentes")
    @ResponseStatus(HttpStatus.OK)
    public void deletar(@RequestBody int id) {
        gerenteServices.delete(Long.valueOf(id));
    }

}
