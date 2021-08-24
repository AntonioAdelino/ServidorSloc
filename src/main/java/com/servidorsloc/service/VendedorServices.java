package com.servidorsloc.service;

import java.util.List;
import java.util.Optional;

import com.servidorsloc.model.Gerente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.servidorsloc.model.Vendedor;
import com.servidorsloc.repository.VendedorRepository;

@Service
public class VendedorServices {
    @Autowired
    private VendedorRepository vendedorRepository;

    public Vendedor login(String email, String senha) {
        List<Vendedor> todosOsVendedores = this.findAll();
        for (int i = 0; i <= todosOsVendedores.size(); ++i) {
            if (todosOsVendedores.get(i).getEmail().equals(email) && todosOsVendedores.get(i).getSenha().equals(senha)) {
                return todosOsVendedores.get(i);
            }
        }
        return null;
    }

    public List<Vendedor> findAll() {
        return (List<Vendedor>) this.vendedorRepository.findAll();
    }

    public Vendedor save(Vendedor vendedor) {
        System.out.print(vendedor);

        return this.vendedorRepository.save(vendedor);
    }

    public Vendedor update(Vendedor vendedor) {
        //buscando o vendedor no banco
        Optional<Vendedor> g = this.vendedorRepository.findById(vendedor.getId());
        Vendedor vendedorBanco = g.get();
        //modificando o vendedor vindo do banco com os novos dados
        vendedorBanco.setNome(vendedor.getNome());
        vendedorBanco.setCpf(vendedor.getCpf());
        vendedorBanco.setEmail(vendedor.getEmail());
        vendedorBanco.setSenha(vendedor.getSenha());

        return this.vendedorRepository.save(vendedorBanco);
    }

    public void delete(long id) {
        this.vendedorRepository.deleteById(id);
    }
}
