/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.service;

import br.projeto.estoque.cdm.model.Cliente;
import br.projeto.estoque.cdm.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 
 */
@Service
public class ClienteService implements Services<Cliente> {

    @Autowired
    ClienteRepository repository;

    @Override
    public Cliente buscarPorId(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return this.repository.findAll();
    }

    @Override
    public Cliente salvarOuAtualizar(Cliente obj) {
        return this.repository.save(obj);
    }

    @Override
    public void deletarPorId(Long id) {
        this.repository.delete(id);
    }

    @Override
    public void deletar(Cliente obj) {
        this.repository.delete(obj);
    }

}
