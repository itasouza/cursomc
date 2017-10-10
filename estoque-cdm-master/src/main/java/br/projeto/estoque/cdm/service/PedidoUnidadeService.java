/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.projeto.estoque.cdm.model.EstoqueUnidade;
import br.projeto.estoque.cdm.model.PedidoUnidade;
import br.projeto.estoque.cdm.model.ProdutoUnidade;
import br.projeto.estoque.cdm.model.Unidade;
import br.projeto.estoque.cdm.model.Usuario;
import br.projeto.estoque.cdm.repository.PedidoUnidadeRepository;

/**
 *
 * 
 */
@Service
public class PedidoUnidadeService implements Services<PedidoUnidade> {

    @Autowired
    PedidoUnidadeRepository repository;
    
    @Autowired
    EstoqueUnidadeService estoqueUnidadeService;

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

    public void atualizaStatus(String statusPedido, Long id) {
        this.repository.updateStatusWhereId(statusPedido, id);
    }
    
    @Transactional
    public String finalizar(PedidoUnidade pedidoUnidade, Usuario usuarioLogado) {
    	String msg = null;
    	PedidoUnidade pedido = this.buscarPorId(pedidoUnidade.getId());
		if(usuarioLogado.getUnidade().getPedidoEspecial()) {
			pedido.setFormaEntrega(pedidoUnidade.getFormaEntrega());
			pedido.setCodigoRastreio(pedidoUnidade.getCodigoRastreio());
			pedido.setStatus("ENVIADO");
			
		}else {
			for (ProdutoUnidade p : pedido.getProdutos()) {
				// por cada produto inserido, cadastrar no stoque do CDM
				EstoqueUnidade estoque = this.estoqueUnidadeService.buscarPorProduto(p.getProduto());
				if (estoque == null) {
					// nao tem o produto no estoque, cadastrar o produto
					EstoqueUnidade eu = new EstoqueUnidade(p.getProduto(), pedido.getUnidade(), p.getQuantidade(), 0);
					this.estoqueUnidadeService.salvarOuAtualizar(eu);
				} else {
					// produto ja existe, atualizar
					estoque.setEstoqueFisico(estoque.getEstoqueFisico() + p.getQuantidade());
					this.estoqueUnidadeService.salvarOuAtualizar(estoque);
				}
			}
			pedido.setStatus("FINALIZADO");
		}
		msg = "Pedido número " + pedidoUnidade.getId() + " enviado com sucesso";
		
		this.salvarOuAtualizar(pedido);
		
		return msg;
    }
}
