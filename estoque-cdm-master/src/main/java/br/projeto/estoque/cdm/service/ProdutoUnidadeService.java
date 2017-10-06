/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.service;

import br.projeto.estoque.cdm.model.ProdutoUnidade;
import br.projeto.estoque.cdm.repository.ProdutoUnidadeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *
 */
@Service
public class ProdutoUnidadeService implements Services<ProdutoUnidade> {

    @Autowired
    ProdutoUnidadeRepository repository;

    @Override
    public ProdutoUnidade buscarPorId(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public List<ProdutoUnidade> buscarTodos() {
        return this.repository.findAll();
    }

    @Override
    public ProdutoUnidade salvarOuAtualizar(ProdutoUnidade obj) {
        if (obj.getId() != null && this.repository.exists(obj.getId())) {
            ProdutoUnidade pu = this.repository.findOne(obj.getId());
            pu.setQuantidade(pu.getQuantidade() + obj.getQuantidade());
            return this.repository.save(pu);
        } else {
            System.out.println("ID nulo, salvando obj");
            return this.repository.save(obj);
        }
    }

    @Override
    public void deletarPorId(Long id) {
        this.repository.delete(id);
    }

    @Override
    public void deletar(ProdutoUnidade obj) {
        this.repository.delete(obj);
    }

}
