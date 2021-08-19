package com.servidorsloc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.servidorsloc.model.Profissional;

public interface ProfissionalRepository extends CrudRepository<Profissional, Long> {

    List<Profissional> findByNome(String nome);

} 

