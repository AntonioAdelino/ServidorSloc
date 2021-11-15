package com.servidorsloc;

import com.servidorsloc.model.Gerente;
import com.servidorsloc.repository.GerenteRepository;
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
public class GerenteRepositoryTest {

    private final String nome = "Antonio", cpf = "123.456.789-00", email = "antonio@gmail.com", senha = "senha123";
    private Gerente gerente = new Gerente(nome, cpf, email, senha);

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Autowired
    private GerenteRepository gerenteRepository;

    @Test
    public void adicionar() {
        gerenteRepository.save(gerente);
        Assertions.assertThat(gerente.getId()).isNotNull();
        Assertions.assertThat(gerente.getNome()).isEqualTo(nome);
        Assertions.assertThat(gerente.getCpf()).isEqualTo(cpf);
        Assertions.assertThat(gerente.getEmail()).isEqualTo(email);
        Assertions.assertThat(gerente.getSenha()).isEqualTo(senha);
    }

    @Test
    public void atualizar() {
        gerenteRepository.save(gerente);
        //Criando um novo gerente
        String nome = "Mateus", cpf = "987.654.321-00", email = "mateus@gmail.com", senha = "123senha";
        //Editando o gerente
        gerente.setNome(nome);
        gerente.setCpf(cpf);
        gerente.setEmail(email);
        gerente.setSenha(senha);
        gerenteRepository.save(gerente);
        //Buscando o gerente atualizado
        gerente = gerenteRepository.findByNome(nome).get(0);
        //Realizando os testes
        Assertions.assertThat(gerente.getNome()).isEqualTo(nome);
        Assertions.assertThat(gerente.getCpf()).isEqualTo(cpf);
        Assertions.assertThat(gerente.getEmail()).isEqualTo(email);
        Assertions.assertThat(gerente.getSenha()).isEqualTo(senha);
    }

    @Test
    public void deletar() {
        gerenteRepository.save(gerente);
        gerenteRepository.delete(gerente);
        Assertions.assertThat(gerenteRepository.findByNome(nome)).isEmpty();
    }
}
