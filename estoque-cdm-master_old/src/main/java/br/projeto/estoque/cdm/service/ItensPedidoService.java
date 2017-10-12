/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.service;

import br.projeto.estoque.cdm.model.ItensPedido;
import br.projeto.estoque.cdm.repository.ItensPedidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 
 */
@Service
public class ItensPedidoService implements Services<ItensPedido> {

    @Autowired
    ItensPedidoRepository repository;

    @Override
    public ItensPedido buscarPorId(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public List<ItensPedido> buscarTodos() {
        return this.repository.findAll();
    }

    @Override
    public ItensPedido salvarOuAtualizar(ItensPedido obj) {
        return this.repository.save(obj);
    }

    @Override
    public void deletarPorId(Long id) {
        this.repository.delete(id);
    }

    @Override
    public void deletar(ItensPedido obj) {
        this.repository.delete(obj);
    }

    public List<ItensPedido> buscarPorPedido(Long id) {
        return this.repository.findByNumeroPedido(id);
    }

}
