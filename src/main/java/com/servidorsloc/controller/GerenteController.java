package com.servidorsloc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.servidorsloc.service.GerenteServices;
import com.servidorsloc.model.Gerente;
 

@RestController
public class GerenteController {
	
	@Autowired
	private GerenteServices gerenteServices;
	
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
