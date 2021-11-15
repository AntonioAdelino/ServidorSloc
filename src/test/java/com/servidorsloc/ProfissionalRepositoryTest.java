package com.servidorsloc;

import com.servidorsloc.model.Profissional;
import com.servidorsloc.repository.GerenteRepository;
import com.servidorsloc.repository.ProfissionalRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProfissionalRepositoryTest {

    private final String idPlace = "A0001", nome = "Restaurante", endereco = "Rua 1, Centro, Garanhuns-PE", contato = "(87)98888888",
            avaliacao = "5/5", latitude = "123456", longitude = "123456";
    private Profissional profissional = new Profissional(idPlace, nome, endereco, contato, avaliacao, latitude, longitude);

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Test
    public void adicionar() {
        profissionalRepository.save(profissional);
        Assertions.assertThat(profissional.getId()).isNotNull();
        Assertions.assertThat(profissional.getIdPlace()).isEqualTo(idPlace);
        Assertions.assertThat(profissional.getNome()).isEqualTo(nome);
        Assertions.assertThat(profissional.getEndereco()).isEqualTo(endereco);
        Assertions.assertThat(profissional.getContato()).isEqualTo(contato);
        Assertions.assertThat(profissional.getAvaliacao()).isEqualTo(avaliacao);
        Assertions.assertThat(profissional.getLatitude()).isEqualTo(latitude);
        Assertions.assertThat(profissional.getLongitude()).isEqualTo(longitude);
    }

    @Test
    public void atualizar() {
        profissionalRepository.save(profissional);
        String idPlace = "A0002", nome = "Mercado", endereco = "Rua 20, Centro, Caruaru-PE", contato = "(81)999999999",
                avaliacao = "3/5", latitude = "654321", longitude = "654321";
        //Editando o gerente
        profissional.setIdPlace(idPlace);
        profissional.setNome(nome);
        profissional.setEndereco(endereco);
        profissional.setContato(contato);
        profissional.setAvaliacao(avaliacao);
        profissional.setLatitude(latitude);
        profissional.setLongitude(longitude);
        //Buscando o profissional atualizado
        profissional = profissionalRepository.findByNome(nome).get(0);
        //Realizando os testes
        Assertions.assertThat(profissional.getIdPlace()).isEqualTo(idPlace);
        Assertions.assertThat(profissional.getNome()).isEqualTo(nome);
        Assertions.assertThat(profissional.getEndereco()).isEqualTo(endereco);
        Assertions.assertThat(profissional.getContato()).isEqualTo(contato);
        Assertions.assertThat(profissional.getAvaliacao()).isEqualTo(avaliacao);
        Assertions.assertThat(profissional.getLatitude()).isEqualTo(latitude);
        Assertions.assertThat(profissional.getLongitude()).isEqualTo(longitude);
    }

    @Test
    public void deletar() {
        profissionalRepository.save(profissional);
        profissionalRepository.delete(profissional);
        Assertions.assertThat(profissionalRepository.findByNome(nome)).isEmpty();
    }
}
