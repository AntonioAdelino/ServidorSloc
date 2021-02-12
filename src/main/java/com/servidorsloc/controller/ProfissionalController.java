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
import com.servidorsloc.service.ProfissionalServices;
import com.servidorsloc.model.Profissional;
 

@RestController
public class ProfissionalController {
	
	@Autowired
	private ProfissionalServices profissionalServices;
	
	@GetMapping("/profissionais")
	public List<Profissional> listAll() {    
	return profissionalServices.findAll();
	}
	
	@PostMapping("/profissionais")
	@ResponseStatus(HttpStatus.CREATED)
	public Profissional adicionar(@RequestBody Profissional profissional) {
		return profissionalServices.save(profissional);	
	}
	
	@PutMapping("/profissionais")
	@ResponseStatus(HttpStatus.OK)
	public Profissional atualizar(@RequestBody Profissional profissional) {
		return profissionalServices.update(profissional);	
	}
	
	
	@DeleteMapping("/profissionais")
	@ResponseStatus(HttpStatus.OK)
	public void deletar(@RequestBody int id) {
		profissionalServices.delete(Long.valueOf(id));	
	}
	
	
}
