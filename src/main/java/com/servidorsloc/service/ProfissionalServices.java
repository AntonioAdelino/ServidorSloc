package com.servidorsloc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.servidorsloc.model.Profissional;
import com.servidorsloc.repository.ProfissionalRepository;

@Service
public class ProfissionalServices {
    @Autowired
    private ProfissionalRepository profissionalRepository;

    public List<Profissional> findAll() {
        return (List<Profissional>) this.profissionalRepository.findAll();
    }

    public Profissional save(Profissional profissional) {
        return this.profissionalRepository.save(profissional);
    }

    public Profissional update(Profissional profissional) {
        //buscando o profissional no banco
        Optional<Profissional> p = this.profissionalRepository.findById(profissional.getId());
        Profissional profissionalBanco = p.get();
        //modificando o profissional vindo do banco com os novos dados
        profissionalBanco.setId(profissional.getId());
        profissionalBanco.setIdPlace(profissional.getIdPlace());
        profissionalBanco.setNome(profissional.getNome());
        profissionalBanco.setEndereco(profissional.getEndereco());
        profissionalBanco.setContato(profissional.getContato());
        profissionalBanco.setAvaliacao(profissional.getAvaliacao());
        profissionalBanco.setLatitude(profissional.getLatitude());
        profissionalBanco.setLongitude(profissional.getLongitude());

        return this.profissionalRepository.save(profissionalBanco);
    }

    public void delete(long id) {
        this.profissionalRepository.deleteById(id);
    }

}
