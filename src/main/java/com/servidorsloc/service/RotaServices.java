package com.servidorsloc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servidorsloc.model.Rota;
import com.servidorsloc.repository.RotaRepository;

@Service
public class RotaServices {
	@Autowired
	private RotaRepository rotaRepository;
	
	
	public List<Rota> findAll(){
		return (List<Rota>) this.rotaRepository.findAll();
	}
	
	public Rota save(Rota rota){
		return this.rotaRepository.save(rota);
	}
	
	public Rota update(Rota rota){
		//buscando o rota no banco
		Optional<Rota> r = this.rotaRepository.findById(rota.getId());
		Rota rotaBanco = r.get();
		//modificando o rota vindo do banco com os novos dados
		rotaBanco.setData(rota.getData());
		rotaBanco.setDistanciaCheckin(rota.getDistanciaCheckin());
		rotaBanco.setGerente(rota.getGerente());
		rotaBanco.setVendedor(rota.getVendedor());
		rotaBanco.setProfissionais(rota.getProfissionais());
		
		return this.rotaRepository.save(rotaBanco);
	}
	
	public void delete(long id){
		this.rotaRepository.deleteById(id);
	}
}
