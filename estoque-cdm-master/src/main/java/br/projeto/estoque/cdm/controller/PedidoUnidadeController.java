/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.controller;

import br.projeto.estoque.cdm.mensagem.FormMensagem;
import br.projeto.estoque.cdm.mensagem.TipoMensagem;
import br.projeto.estoque.cdm.model.EstoqueUnidade;
import br.projeto.estoque.cdm.model.Pedido;
import br.projeto.estoque.cdm.model.PedidoUnidade;
import br.projeto.estoque.cdm.model.Produto;
import br.projeto.estoque.cdm.model.ProdutoModal;
import br.projeto.estoque.cdm.model.ProdutoUnidade;
import br.projeto.estoque.cdm.model.Usuario;
import br.projeto.estoque.cdm.service.EstoqueUnidadeService;
import br.projeto.estoque.cdm.service.FormaEntregaService;
import br.projeto.estoque.cdm.service.PedidoUnidadeService;
import br.projeto.estoque.cdm.service.ProdutoService;
import br.projeto.estoque.cdm.service.ProdutoUnidadeService;
import br.projeto.estoque.cdm.service.TransportadoraService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 *
 */
@Controller
@RequestMapping("/pedidosunidade")
public class PedidoUnidadeController {

    FormMensagem msg;

    @Autowired
    PedidoUnidadeService pedidoUnidadeService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ProdutoUnidadeService produtoUnidadeService;

    @Autowired
    EstoqueUnidadeService estoqueUnidadeService;

    @Autowired
    FormaEntregaService formaEntregaService;

    @Autowired
    TransportadoraService transportadoraService;

//    PedidoUnidade pedido = new PedidoUnidade();
    @GetMapping
    public ModelAndView form(@AuthenticationPrincipal Usuario usuarioLogado, @RequestParam(defaultValue = "0") Integer page) {

        ModelAndView model = new ModelAndView("pedidounidade/visualizar-pedidounidade");

        if (usuarioLogado.getUnidade().getPedidoEspecial()) {
            // se for fabrica
            model.addObject("pedidos", this.pedidoUnidadeService.buscarTodos(page, 10));
        } else {
            model.addObject("pedidos", this.pedidoUnidadeService.buscarPorUnidade(usuarioLogado.getUnidade(), page, 10));
        }
        model.addObject("user", usuarioLogado);

        return model;
    }

    @GetMapping("/novo")
    public ModelAndView novoPedido(@AuthenticationPrincipal Usuario usuarioLogado) {

        if (usuarioLogado.getUnidade().getPedidoEspecial()) {
            // fabrica
            return new ModelAndView("redirect:/pedidosunidade");
        }

        ModelAndView model = new ModelAndView("pedidounidade/form-pedidounidade");

        usuarioLogado.getPedido().atualizarValor();
        model.addObject("pedido", usuarioLogado.getPedido());
        model.addObject("user", usuarioLogado);
        model.addObject("produtos", this.produtoService.buscarTodos());
        model.addObject("produtoSelecao", new ProdutoModal());

        return model;
    }

    @GetMapping("/remover/{id}")
    public ModelAndView removerProdutoPedido(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {
        ModelAndView model = new ModelAndView("redirect:/pedidosunidade/novo");

        for (ProdutoUnidade p : usuarioLogado.getPedido().getProdutos()) {
            if (p.getProduto().getId() == id) {
                usuarioLogado.getPedido().getProdutos().remove(p);
                break;
            }
        }

        return model;
    }

    @PostMapping("/produto")
    public ModelAndView adicionarProduto(ProdutoModal selecao, @AuthenticationPrincipal Usuario usuarioLogado) {
        ModelAndView model = new ModelAndView("redirect:/pedidosunidade/novo");
        Boolean achou = false;

        if (selecao.getQuantidade() > 0) {
            Produto produto = this.produtoService.buscarPorId(selecao.getId());

            ProdutoUnidade ps = new ProdutoUnidade(produto, selecao.getQuantidade());
            for (ProdutoUnidade prod : usuarioLogado.getPedido().getProdutos()) {
                if (prod.getProduto().getId() == ps.getProduto().getId()) {
                    // ja tem o produto na lista
                    achou = true;
                }
            }

            if (achou) {
                System.out.println("Ja tem esse produto na lista");
                // ja tem esse cara la
                for (ProdutoUnidade p : usuarioLogado.getPedido().getProdutos()) {
                    if (ps.equals(p)) {
                        System.out.println("atualizar o valor da lista");
                        // achei o cara dentro da lista, atualiza a quantidade
                        p.setQuantidade(p.getQuantidade() + ps.getQuantidade());
                        break;
                    }
                }
            } else {
                System.out.println("nao achou o produto na lista. Adicionando");
                usuarioLogado.getPedido().getProdutos().add(ps);
            }
        }
        return model;
    }

    @GetMapping("/{id}")
    public ModelAndView visualizar(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {
        ModelAndView model = new ModelAndView("pedidounidade/visualiza-pedido");
        model.addObject("pedido", this.pedidoUnidadeService.buscarPorId(id));
        model.addObject("user", usuarioLogado);
        model.addObject("formasEntrega", this.formaEntregaService.buscarTodos());
        model.addObject("transportadoras", this.transportadoraService.buscarTodos());
        return model;
    }

    @PostMapping
    public ModelAndView cadastrar(PedidoUnidade pedidoUnidade, @AuthenticationPrincipal Usuario usuarioLogado, RedirectAttributes attributes) {
        ModelAndView model = new ModelAndView("redirect:/pedidosunidade");
        model.addObject("user", usuarioLogado);
        attributes.addFlashAttribute("user", usuarioLogado);
        PedidoUnidade pedido = usuarioLogado.getPedido();
        try {
            //pedido.setId(null);
            pedido.setUsuario(usuarioLogado);
            pedido.setStatus("ABERTO");
            pedido.setDataPedido(Calendar.getInstance());
            pedido.setUnidade(usuarioLogado.getUnidade());
            ArrayList<ProdutoUnidade> produtoUnidades = new ArrayList<>();

            // registrar os produtos e quantidade
            for (ProdutoUnidade p : pedido.getProdutos()) {
                System.out.println("Salvando produto " + p.getId() + " " + p.getProduto().getId() + " " + p.getProduto().getNome());
                produtoUnidades.add(this.produtoUnidadeService.salvarOuAtualizar(p));
            }
            pedido.setProdutos(produtoUnidades);

            pedido = this.pedidoUnidadeService.salvarOuAtualizar(pedido);
            msg = new FormMensagem(TipoMensagem.SUCESSO).addMensagem("Pedido #" + pedido.getId() + " registrado com sucesso");
        } catch (Exception e) {
            System.out.println("Erro " + e);
            msg = new FormMensagem(TipoMensagem.ERRO).addMensagem("Não foi possível registrar o pedido");
        }
        usuarioLogado.setPedido(new PedidoUnidade());
        attributes.addFlashAttribute("msg", msg);
        return model;

    }

    @PostMapping("/finalizar/{id}")
    public ModelAndView finalizarPedido(PedidoUnidade pedidoUnidade, @PathVariable Long id, RedirectAttributes attributes, @AuthenticationPrincipal Usuario usuarioLogado) {
        ModelAndView model = new ModelAndView("redirect:/pedidosunidade");
        try {
            PedidoUnidade pedido = this.pedidoUnidadeService.buscarPorId(id);
            if (usuarioLogado.getUnidade().getPedidoEspecial()) {
                // usuario fabrica colocando comoo enviado
                pedido.setFormaEntrega(pedidoUnidade.getFormaEntrega());
                if (pedidoUnidade.getFormaEntrega().getId() == 1) {
                    pedido.setCodigoRastreio(pedidoUnidade.getCodigoRastreio());
                    pedido.setTransportadora(null);
                    pedido.setNomeEntrega(null);
                    pedido.setRgEntrega(null);
                    pedido.setOrdemColetaEntrega(null);
                } else if (pedidoUnidade.getFormaEntrega().getId() == 2) {
                    pedido.setCodigoRastreio(null);
                    pedido.setTransportadora(pedidoUnidade.getTransportadora());
                    pedido.setNomeEntrega(pedidoUnidade.getNomeEntrega());
                    pedido.setRgEntrega(pedidoUnidade.getRgEntrega());
                    pedido.setOrdemColetaEntrega(pedidoUnidade.getOrdemColetaEntrega());
                } else if (pedidoUnidade.getFormaEntrega().getId() == 3) {
                    pedido.setCodigoRastreio(null);
                    pedido.setTransportadora(null);
                    pedido.setNomeEntrega(pedidoUnidade.getNomeEntrega());
                    pedido.setRgEntrega(pedidoUnidade.getRgEntrega());
                    pedido.setOrdemColetaEntrega(null);
                }

                pedido.setStatus("ENVIADO");
                msg = new FormMensagem(TipoMensagem.SUCESSO).addMensagem("Pedido número " + pedidoUnidade.getId() + " enviado com sucesso");
            } else {
                // usuario normal colocando como entregue
                for (ProdutoUnidade p : pedido.getProdutos()) {
                    // por cada produto inserido, cadastrar no stoque do CDM
                    EstoqueUnidade estoque = this.estoqueUnidadeService.buscarPorProdutoEUnidade(p.getProduto(), usuarioLogado.getUnidade());
                    if (estoque == null) {
                        // nao tem o produto no estoque, cadastrar o produto
                        EstoqueUnidade eu = new EstoqueUnidade(p.getProduto(), pedido.getUnidade(), (p.getQuantidade() * p.getProduto().getQtdcaixa()), 0);
                        eu.setUnidade(usuarioLogado.getUnidade());
                        this.estoqueUnidadeService.salvarOuAtualizar(eu);
                    } else {
                        estoque.setUnidade(usuarioLogado.getUnidade());
                        // produto ja existe, atualizar
                        estoque.setEstoqueFisico(estoque.getEstoqueFisico() + (p.getQuantidade() * p.getProduto().getQtdcaixa()));
                        this.estoqueUnidadeService.salvarOuAtualizar(estoque);
                    }
                }

                pedido.setStatus("FINALIZADO");
                msg = new FormMensagem(TipoMensagem.SUCESSO).addMensagem("Pedido número " + id + " finalizado com sucesso");
            }
            this.pedidoUnidadeService.salvarOuAtualizar(pedido);
        } catch (Exception e) {
            System.out.println("Erro " + e);
            msg = new FormMensagem(TipoMensagem.ERRO).addMensagem("Não foi possivel finalizar o pedido");
        }
        attributes.addFlashAttribute("msg", msg);
        return model;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {
        ModelAndView model = new ModelAndView("pedidounidade/form-pedidounidade");
        PedidoUnidade pedidoUnidade = null;
        //if(id > 0 && usuarioLogado.getPedido() == null) {
        if (id > 0) {
            pedidoUnidade = this.pedidoUnidadeService.buscarPorId(id);
            usuarioLogado.setPedido(pedidoUnidade);
        } else {
            pedidoUnidade = usuarioLogado.getPedido();
        }

        usuarioLogado.getPedido().atualizarValor();

        model.addObject("pedido", pedidoUnidade);
        model.addObject("user", usuarioLogado);
        model.addObject("produtos", this.produtoService.buscarTodos());
        model.addObject("produtoSelecao", new ProdutoModal());

        return model;
    }
}
