package com.servidorsloc.service;

import com.servidorsloc.model.Visita;
import com.servidorsloc.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitaServices {
    @Autowired
    private VisitaRepository visitaRepository;

    public List<Visita> findAll() {
        return (List<Visita>) this.visitaRepository.findAll();
    }

    public Visita save(Visita visita) {
        return this.visitaRepository.save(visita);
    }

    public Visita update(Visita visita) {
        //buscando a Visita no banco
        Optional<Visita> v = this.visitaRepository.findById(visita.getId());
        Visita visitaBanco = v.get();
        //modificando a Visita vinda do banco com os novos dados
        visitaBanco.setDistanciaCheckin(visita.getDistanciaCheckin());
        visitaBanco.setRota(visita.getRota());
        visitaBanco.setProfissional(visita.getProfissional());

        return this.visitaRepository.save(visitaBanco);
    }

    public void delete(long id) {
        this.visitaRepository.deleteById(id);
    }
}
