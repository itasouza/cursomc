/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.service;

import br.projeto.estoque.cdm.model.PedidoUnidade;
import br.projeto.estoque.cdm.model.StatusPedido;
import br.projeto.estoque.cdm.model.Unidade;
import br.projeto.estoque.cdm.repository.PedidoUnidadeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 
 */
@Service
public class PedidoUnidadeService implements Services<PedidoUnidade> {

    @Autowired
    PedidoUnidadeRepository repository;

    @Override
    public PedidoUnidade buscarPorId(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public List<PedidoUnidade> buscarTodos() {
        return this.repository.findAll();
    }

    @Override
    public PedidoUnidade salvarOuAtualizar(PedidoUnidade obj) {
        return this.repository.save(obj);
    }

    @Override
    public void deletarPorId(Long id) {
        this.repository.delete(id);
    }

    @Override
    public void deletar(PedidoUnidade obj) {
        this.repository.delete(obj);
    }

    public List<PedidoUnidade> buscarPorUnidade(Unidade unidade) {
        return this.repository.findByUnidade(unidade);
    }

    public void atualizaStatus(StatusPedido statusPedido, Long id) {
        this.repository.updateStatusWhereId(statusPedido, id);
    }
}
