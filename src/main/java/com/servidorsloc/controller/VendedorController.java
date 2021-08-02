package com.servidorsloc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@GetMapping("/vendedores")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Vendedor> listAll() {    
	return vendedorServices.findAll();
	}
	
	@PostMapping("/vendedores")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public Vendedor adicionar(@RequestBody Vendedor vendedor) {
		return vendedorServices.save(vendedor);	
	}
	
	@PutMapping("/vendedores")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public Vendedor atualizar(@RequestBody Vendedor vendedor) {
		return vendedorServices.update(vendedor);	
	}

	@DeleteMapping("/vendedores")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public void deletar(@RequestBody int id) {
		vendedorServices.delete(Long.valueOf(id));	
	}
	
	
}
