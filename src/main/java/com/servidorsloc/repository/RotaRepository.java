package com.servidorsloc.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.servidorsloc.model.Rota;

public interface RotaRepository extends CrudRepository<Rota, Long> {
	   
	List<Rota> findByData(String data);
 
} 

