package com.servidorsloc;

import com.servidorsloc.model.Gerente;
import com.servidorsloc.model.Vendedor;
import com.servidorsloc.repository.GerenteRepository;
import com.servidorsloc.repository.VendedorRepository;
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
public class VendedorRepositoryTest {

    private final String nomeGerente = "Antonio", cpfGerente = "123.456.789-00", emailGerente = "antonio@gmail.com", senhaGerente = "senha123";
    private final String nomeVendedor = "Jorge", cpfVendedor = "555.444.333-11", emailVendedor = "jorge@gmail.com", senhaVendedor = "senha456";
    private final Gerente gerente = new Gerente(nomeGerente, cpfGerente, emailGerente, senhaGerente);
    private Vendedor vendedor = new Vendedor(nomeVendedor, cpfVendedor, emailVendedor, senhaVendedor, gerente);

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private GerenteRepository gerenteRepository;

    @Test
    public void adicionar() {
        vendedorRepository.save(vendedor);
        Assertions.assertThat(vendedor.getId()).isNotNull();
        Assertions.assertThat(vendedor.getNome()).isEqualTo(nomeVendedor);
        Assertions.assertThat(vendedor.getCpf()).isEqualTo(cpfVendedor);
        Assertions.assertThat(vendedor.getEmail()).isEqualTo(emailVendedor);
        Assertions.assertThat(vendedor.getSenha()).isEqualTo(senhaVendedor);
        Assertions.assertThat(vendedor.getGerente()).isEqualTo(gerente);
    }

    @Test
    public void atualizar() {
        vendedorRepository.save(vendedor);
        String nome = "Mateus", cpf = "987.654.321-00", email = "mateus@gmail.com", senha = "123senha";
        //Criando um novo gerente
        Gerente gerente = new Gerente("Lucas", "222.333.111-00", "lucas@hotmail.com", "lucas123");
        gerenteRepository.save(gerente);
        //Editando o vendedor
        vendedor.setNome(nome);
        vendedor.setCpf(cpf);
        vendedor.setEmail(email);
        vendedor.setSenha(senha);
        vendedor.setGerente(gerente);
        vendedorRepository.save(vendedor);
        //Buscando o vendedor atualizado
        vendedor = vendedorRepository.findByNome(nome).get(0);
        //Realizando os testes
        Assertions.assertThat(vendedor.getNome()).isEqualTo(nome);
        Assertions.assertThat(vendedor.getCpf()).isEqualTo(cpf);
        Assertions.assertThat(vendedor.getEmail()).isEqualTo(email);
        Assertions.assertThat(vendedor.getSenha()).isEqualTo(senha);
        Assertions.assertThat(vendedor.getGerente()).isEqualTo(gerente);
    }

    @Test
    public void deletar() {
        vendedorRepository.save(vendedor);
        vendedorRepository.delete(vendedor);
        Assertions.assertThat(vendedorRepository.findByNome(nomeVendedor)).isEmpty();
    }
}
