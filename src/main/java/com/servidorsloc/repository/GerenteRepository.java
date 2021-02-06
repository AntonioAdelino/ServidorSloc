package com.servidorsloc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.servidorsloc.model.Gerente;

public interface GerenteRepository extends CrudRepository<Gerente, Long> {
	   
	List<Gerente> findByNome(String nome);
 
} 

