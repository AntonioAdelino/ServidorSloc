package com.servidorsloc.controller;

import java.util.List;

import com.servidorsloc.model.Gerente;
import com.servidorsloc.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.servidorsloc.service.VendedorServices;
import com.servidorsloc.model.Vendedor;


@RestController
public class VendedorController {

    @Autowired
    private VendedorServices vendedorServices;

    @PostMapping("/vendedores/login")
    public Vendedor login(@RequestBody Login login) {
        return vendedorServices.login(login.getEmail(), login.getSenha());
    }

    @PostMapping("/vendedores/por-gerente")
    public List<Vendedor> login(@RequestBody int gerente) {
        return vendedorServices.vendedoresPorGerente(gerente);
    }

    @GetMapping("/vendedores")
    public List<Vendedor> listAll() {
        return vendedorServices.findAll();
    }

    @PostMapping("/vendedores")
    @ResponseStatus(HttpStatus.CREATED)
    public Vendedor adicionar(@RequestBody Vendedor vendedor) {
        return vendedorServices.save(vendedor);
    }

    @PutMapping("/vendedores")
    @ResponseStatus(HttpStatus.OK)
    public Vendedor atualizar(@RequestBody Vendedor vendedor) {
        return vendedorServices.update(vendedor);
    }

    @DeleteMapping("/vendedores")
    @ResponseStatus(HttpStatus.OK)
    public void deletar(@RequestBody int id) {
        vendedorServices.delete(Long.valueOf(id));
    }

}
