package com.servidorsloc;

import com.servidorsloc.model.Gerente;
import com.servidorsloc.model.Profissional;
import com.servidorsloc.model.Rota;
import com.servidorsloc.model.Vendedor;
import com.servidorsloc.repository.RotaRepository;
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
public class RotaRepositoryTest {

    private final String nomeGerente = "Antonio", cpfGerente = "123.456.789-00", emailGerente = "antonio@gmail.com", senhaGerente = "senha123";
    private final String nomeVendedor = "Jorge", cpfVendedor = "555.444.333-11", emailVendedor = "jorge@gmail.com", senhaVendedor = "senha456";
    private final String idPlace = "A0001", nome = "Restaurante", endereco = "Rua 1, Centro, Garanhuns-PE", contato = "(87)98888888",
            avaliacao = "5/5", latitude = "123456", longitude = "123456";
    private final Gerente gerente = new Gerente(nomeGerente, cpfGerente, emailGerente, senhaGerente);
    private final Vendedor vendedor = new Vendedor(nomeVendedor, cpfVendedor, emailVendedor, senhaVendedor, gerente);
    private final List<Profissional> profissionais = new ArrayList<>();
    private final Profissional profissional = new Profissional(idPlace, nome, endereco, contato, avaliacao, latitude, longitude);
    private final Rota rota;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Autowired
    private RotaRepository rotaRepository;

    {
        profissionais.add(profissional);
        rota = new Rota("01/10/2021", vendedor, profissionais);
    }

    @Test
    public void adicionar() {
        rotaRepository.save(rota);
        Assertions.assertThat(rota.getId()).isNotNull();
        Assertions.assertThat(rota.getData()).isEqualTo("01/10/2021");
        Assertions.assertThat(rota.getVendedor()).isEqualTo(vendedor);
        Assertions.assertThat(rota.getProfissionais()).contains(profissional);
    }

    @Test
    public void deletar() {
        rotaRepository.save(rota);
        rotaRepository.delete(rota);
        Assertions.assertThat(rotaRepository.findByData("21/12/2021")).isEmpty();
    }
}
