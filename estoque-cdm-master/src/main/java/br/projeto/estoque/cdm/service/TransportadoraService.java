/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.service;

import br.projeto.estoque.cdm.model.Transportadora;
import br.projeto.estoque.cdm.repository.TransportadoraRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 *
 */
@Service
public class TransportadoraService implements Services<Transportadora> { 

    @Autowired
    TransportadoraRepository repository;

    @Override
    public Transportadora buscarPorId(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public List<Transportadora> buscarTodos() {        
        Sort sort = new Sort(Sort.Direction.ASC, "nome");
        return this.repository.findAll(sort);
    }

    @Override
    public Transportadora salvarOuAtualizar(Transportadora obj) {
        return this.repository.save(obj);
    }

    @Override
    public void deletarPorId(Long id) {
        this.repository.delete(id);
    }

    @Override
    public void deletar(Transportadora obj) {
        this.repository.delete(obj);
    }

}
