/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.service;

import br.projeto.estoque.cdm.model.EstoqueUnidade;
import br.projeto.estoque.cdm.model.Produto;
import br.projeto.estoque.cdm.model.Unidade;
import br.projeto.estoque.cdm.repository.EstoqueUnidadeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 
 */
@Service
public class EstoqueUnidadeService implements Services<EstoqueUnidade> {

    @Autowired
    EstoqueUnidadeRepository repository;

    public List<EstoqueUnidade> buscarPorUnidade(Unidade unidade) {
        return this.repository.findByUnidade(unidade);
    }

    @Override
    public EstoqueUnidade buscarPorId(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public List<EstoqueUnidade> buscarTodos() {
        return this.repository.findAll();
    }

    public EstoqueUnidade salvarOuAtualizar(EstoqueUnidade obj, Unidade unidade) {
        EstoqueUnidade u = this.repository.findByProdutoAndUnidade(obj.getProduto(), unidade);
        if (u == null) {
            return this.repository.save(obj);
        } else {
            u.setEstoqueFisico(u.getEstoqueFisico() + obj.getEstoqueFisico());
//            u.setEstoqueMinimo(obj.getEstoqueMinimo());
            return this.repository.save(u);
        }
    }
    @Override
    public EstoqueUnidade salvarOuAtualizar(EstoqueUnidade obj) {
//        EstoqueUnidade u = this.repository.findByProduto(obj.getProduto());
//        if (u == null) {
            return this.repository.save(obj);
//        } else {
//            u.setEstoqueFisico(u.getEstoqueFisico() + obj.getEstoqueFisico());
//            u.setEstoqueMinimo(obj.getEstoqueMinimo());
//            return this.repository.save(u);
//        }
    }

    @Override
    public void deletarPorId(Long id) {
        this.repository.delete(id);
    }

    @Override
    public void deletar(EstoqueUnidade obj) {
        this.repository.delete(obj);
    }

    public EstoqueUnidade buscarPorProdutoEUnidade(Produto produto, Unidade unidade) {
        return this.repository.findByProdutoAndUnidade(produto,unidade);
    }


}
