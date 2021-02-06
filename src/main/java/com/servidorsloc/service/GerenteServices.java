package com.servidorsloc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.servidorsloc.model.Gerente;
import com.servidorsloc.repository.GerenteRepository;

@Service
public class GerenteServices {
	@Autowired
	private GerenteRepository gerenteRepository;
	
	
	public List<Gerente> findAll(){
		return (List<Gerente>) this.gerenteRepository.findAll();
	}
	
	public Gerente save(Gerente gerente){
		return this.gerenteRepository.save(gerente);
	}
	
	public Gerente update(Gerente gerente){
		//buscando o gerente no banco
		Optional<Gerente> g = this.gerenteRepository.findById(gerente.getId());
		Gerente gerenteBanco = g.get();
		//modificando o gerente vindo do banco com os novos dados
		gerenteBanco.setNome(gerente.getNome());
		gerenteBanco.setCpf(gerente.getCpf());
		gerenteBanco.setEmail(gerente.getEmail());
		gerenteBanco.setSenha(gerente.getSenha());
		
		return this.gerenteRepository.save(gerenteBanco);
	}
	
	public void delete(long id){
		this.gerenteRepository.deleteById(id);
	}
}
