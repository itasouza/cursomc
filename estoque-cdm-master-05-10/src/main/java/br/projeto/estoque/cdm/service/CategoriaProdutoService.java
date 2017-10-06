/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.service;

import br.projeto.estoque.cdm.model.CategoriaProduto;
import br.projeto.estoque.cdm.repository.CategoriaProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 
 */
@Service
public class CategoriaProdutoService implements Services<CategoriaProduto> {

    @Autowired
    CategoriaProdutoRepository repository;

    @Override
    public CategoriaProduto buscarPorId(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public List<CategoriaProduto> buscarTodos() {
        return this.repository.findAll();
    }

    @Override
    public CategoriaProduto salvarOuAtualizar(CategoriaProduto obj) {
        return this.repository.save(obj);
    }

    @Override
    public void deletarPorId(Long id) {
        this.repository.delete(id);
    }

    @Override
    public void deletar(CategoriaProduto obj) {
        this.repository.delete(obj);
    }

}
