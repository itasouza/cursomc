/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.controller;

import br.projeto.estoque.cdm.mensagem.FormMensagem;
import br.projeto.estoque.cdm.mensagem.TipoMensagem;
import br.projeto.estoque.cdm.model.EstoqueUnidade;
import br.projeto.estoque.cdm.model.ItensPedido;
import br.projeto.estoque.cdm.model.Pedido;
import br.projeto.estoque.cdm.model.Produto;
import br.projeto.estoque.cdm.model.ProdutoUnidade;
import br.projeto.estoque.cdm.model.Usuario;
import br.projeto.estoque.cdm.service.EstoqueUnidadeService;
import br.projeto.estoque.cdm.service.ItensPedidoService;
import br.projeto.estoque.cdm.service.PedidoService;
import br.projeto.estoque.cdm.service.ProdutoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 *
 */
@Controller
@RequestMapping("/pedidos")
public class PedidoClienteController {

    FormMensagem msg = null;

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ItensPedidoService itensPedidoService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    EstoqueUnidadeService estoqueUnidadeService;

    @GetMapping
    public ModelAndView form(@AuthenticationPrincipal Usuario usuarioLogado) {

        ModelAndView model = new ModelAndView("pedido/form-pedido");

        model.addObject("pedidos", this.pedidoService.buscarPorUnidade(usuarioLogado.getUnidade()));

        model.addObject("user", usuarioLogado);

        return model;
    }

    @GetMapping("/{id}")
    public ModelAndView visualizar(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {
        ModelAndView model = new ModelAndView("pedido/visualiza-pedido");

        List<ItensPedido> itens = this.itensPedidoService.buscarPorPedido(id);

//        for (ItensPedido ip : itens) {
//            Produto p = this.produtoService.buscarPorId(ip.getIdProduto());
//            ProdutoUnidade produtoSelecao = new ProdutoUnidade(p, ip.getQtde().intValue());
//            if (p != null) {
//                produtos.add(produtoSelecao);
//            }
//        }
        System.out.println("Buscando pedido numero " + id);
        model.addObject("pedido", this.pedidoService.buscarPorId(id));
        model.addObject("produtos", itens);
        model.addObject("user", usuarioLogado);
        return model;
    }

    @PostMapping("/finalizar/{id}")
    public ModelAndView finalizarPedido(@PathVariable Long id, RedirectAttributes attributes, @AuthenticationPrincipal Usuario usuarioLogado) {
        ModelAndView model = new ModelAndView("redirect:/pedidos");
        List<EstoqueUnidade> itensAlterados = new ArrayList<>();

        try {
            List<ItensPedido> itens = this.itensPedidoService.buscarPorPedido(id);

            for (ItensPedido i : itens) {
                Produto produto = this.produtoService.buscarPorId(i.getIdProduto());
                EstoqueUnidade estoque = this.estoqueUnidadeService.buscarPorProdutoEUnidade(produto, usuarioLogado.getUnidade());
                if (estoque != null && estoque.getEstoqueFisico() >= i.getQtde()) {
                    // se for maior, decrementar e salvar
                    estoque.setEstoqueFisico(estoque.getEstoqueFisico() - i.getQtde().intValue());
                    itensAlterados.add(estoque);
                } else {
                    // nao tem estoque para finalizar o pedido
                    this.msg = new FormMensagem(TipoMensagem.ERRO).addMensagem("Não foi possível finalizar o pedido").addMensagem("Produto " + produto.getNome() + " não possui estoque suficiente para dar saída");
                    attributes.addFlashAttribute("msg", msg);
                    return model;
                }
            }
            for (EstoqueUnidade eu : itensAlterados) {
                this.estoqueUnidadeService.salvarOuAtualizar(eu);
            }
            this.pedidoService.atualizarStatus(id, "FINALIZADO");
            this.msg = new FormMensagem(TipoMensagem.SUCESSO).addMensagem("Pedido número #" + id + " finalizado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro " + e);
            msg = new FormMensagem(TipoMensagem.ERRO).addMensagem("Não foi possível finalizar este pedido");
        }
        attributes.addFlashAttribute("msg", msg);
        return model;
    }
}
