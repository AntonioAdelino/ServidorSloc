package com.servidorsloc.controller;

import com.servidorsloc.model.Visita;
import com.servidorsloc.service.VisitaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VisitaController {

    @Autowired
    private VisitaServices visitaServices;

    @GetMapping("/visitas")
    public List<Visita> listAll() {
        return visitaServices.findAll();
    }

    @PostMapping("/visitas")
    @ResponseStatus(HttpStatus.CREATED)
    public Visita adicionar(@RequestBody Visita visita) {
        return visitaServices.save(visita);
    }

    @PutMapping("/visitas")
    @ResponseStatus(HttpStatus.OK)
    public Visita atualizar(@RequestBody Visita visita) {
        return visitaServices.update(visita);
    }

    @DeleteMapping("/visitas")
    @ResponseStatus(HttpStatus.OK)
    public void deletar(@RequestBody int id) {
        visitaServices.delete(Long.valueOf(id));
    }

}
