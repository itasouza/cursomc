/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.service;

import br.projeto.estoque.cdm.model.Cidade;
import br.projeto.estoque.cdm.repository.CidadeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 *
 */
@Service
public class CidadeService implements Services<Cidade> { 

    @Autowired
    CidadeRepository repository;

    @Override
    public Cidade buscarPorId(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public List<Cidade> buscarTodos() {        
        Sort sort = new Sort(Sort.Direction.ASC, "nome");
        return this.repository.findAll(sort);
    }

    @Override
    public Cidade salvarOuAtualizar(Cidade obj) {
        return this.repository.save(obj);
    }

    @Override
    public void deletarPorId(Long id) {
        this.repository.delete(id);
    }

    @Override
    public void deletar(Cidade obj) {
        this.repository.delete(obj);
    }

}
