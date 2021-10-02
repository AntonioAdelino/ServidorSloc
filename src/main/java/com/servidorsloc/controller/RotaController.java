package com.servidorsloc.controller;

import java.util.List;

import com.servidorsloc.model.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.servidorsloc.service.RotaServices;
import com.servidorsloc.model.Rota;


@RestController
public class RotaController {

    @Autowired
    private RotaServices rotaServices;

    @GetMapping("/rotas")
    public List<Rota> listAll() {
        return rotaServices.findAll();
    }

    @PostMapping("/rotas/por-vendedor")
    public List<Rota> rotasPorVendedor(@RequestBody int vendedor) {
        return rotaServices.rotasPorVendedor(vendedor);
    }

    @PostMapping("/rotas/quantidade-por-vendedor")
    public int quatidadeDeRotasPorVendedor(@RequestBody int vendedor) {
        return rotaServices.quatidadeDeRotasPorVendedor(vendedor);
    }

    @PostMapping("/rotas/quantidade-profissionais-por-vendedor")
    public int quatidadeDeProfissionaisVisitadosPorVendedor(@RequestBody int vendedor) {
        return rotaServices.quatidadeDeProfissionaisVisitadosPorVendedor(vendedor);
    }

    @PostMapping("/rotas")
    @ResponseStatus(HttpStatus.CREATED)
    public Rota adicionar(@RequestBody Rota rota) {
        return rotaServices.save(rota);
    }

    @PutMapping("/rotas")
    @ResponseStatus(HttpStatus.OK)
    public Rota atualizar(@RequestBody Rota rota) {
        return rotaServices.update(rota);
    }

    @DeleteMapping("/rotas")
    @ResponseStatus(HttpStatus.OK)
    public void deletar(@RequestBody int id) {
        rotaServices.delete(Long.valueOf(id));
    }

}
