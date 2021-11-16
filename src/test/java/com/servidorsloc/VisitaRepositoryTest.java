package com.servidorsloc;

import com.servidorsloc.model.*;
import com.servidorsloc.repository.VisitaRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VisitaRepositoryTest {

    private final String nomeGerente = "Antonio", cpfGerente = "123.456.789-00", emailGerente = "antonio@gmail.com", senhaGerente = "senha123";
    private final String nomeVendedor = "Jorge", cpfVendedor = "555.444.333-11", emailVendedor = "jorge@gmail.com", senhaVendedor = "senha456";
    private final String idPlace = "A0001", nome = "Restaurante", endereco = "Rua 1, Centro, Garanhuns-PE", contato = "(87)98888888",
            avaliacao = "5/5", latitude = "123456", longitude = "123456";
    private final Gerente gerente = new Gerente(nomeGerente, cpfGerente, emailGerente, senhaGerente);
    private final Vendedor vendedor = new Vendedor(nomeVendedor, cpfVendedor, emailVendedor, senhaVendedor, gerente);
    private final List<Profissional> profissionais = new ArrayList<>();
    private final Profissional profissional = new Profissional(idPlace, nome, endereco, contato, avaliacao, latitude, longitude);
    private final Visita visita;
    private final Rota rota;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Autowired
    private VisitaRepository visitaRepository;

    {
        profissionais.add(profissional);
        rota = new Rota("01/10/2021", vendedor, profissionais);
        visita = new Visita(100, rota, profissional);
    }

    @Test
    public void adicionar() {
        visitaRepository.save(visita);
        Assertions.assertThat(visita.getId()).isNotNull();
        Assertions.assertThat(visita.getDistanciaCheckin()).isEqualTo(100);
        Assertions.assertThat(visita.getRota()).isEqualTo(rota);
        Assertions.assertThat(visita.getProfissional()).isEqualTo(profissional);
    }

    @Test
    public void deletar() {
        visitaRepository.save(visita);
        visitaRepository.delete(visita);
        Assertions.assertThat(visitaRepository.findAll()).isEmpty();
    }
}
