/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.service;

import br.projeto.estoque.cdm.model.Produto;
import br.projeto.estoque.cdm.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 *
 */
@Service
public class ProdutoService implements Services<Produto> {

    @Autowired
    ProdutoRepository repository;

    @Override
    public Produto buscarPorId(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public List<Produto> buscarTodos() {
        Sort sort = new Sort(Sort.Direction.ASC, "nome");
        return this.repository.findAll(sort);
    }

    @Override
    public Produto salvarOuAtualizar(Produto obj) {
        return this.repository.save(obj);
    }

    @Override
    public void deletarPorId(Long id) {
        this.repository.delete(id);
    }

    @Override
    public void deletar(Produto obj) {
        this.repository.delete(obj);
    }

    public long contarRegistros() {
        return this.repository.count();
    }

    public Page<Produto> buscarPaginacao(PageRequest page) {
        return this.repository.findAll(page);
    }

}
