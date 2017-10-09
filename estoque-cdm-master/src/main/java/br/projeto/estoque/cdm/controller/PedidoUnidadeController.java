/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.controller;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.projeto.estoque.cdm.mensagem.FormMensagem;
import br.projeto.estoque.cdm.mensagem.TipoMensagem;
import br.projeto.estoque.cdm.model.EstoqueUnidade;
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


	//    PedidoUnidade pedido = new PedidoUnidade();
	@GetMapping
	public ModelAndView form(@AuthenticationPrincipal Usuario usuarioLogado) {
		usuarioLogado.setPedido(null);
		ModelAndView model = new ModelAndView("pedidounidade/visualizar-pedidounidade");

		if (usuarioLogado.getUnidade().getPedidoEspecial()) {
			// se for fabrica
			model.addObject("pedidos", this.pedidoUnidadeService.buscarTodos());
		} else {
			model.addObject("pedidos", this.pedidoUnidadeService.buscarPorUnidade(usuarioLogado.getUnidade()));
		}
		model.addObject("user", usuarioLogado);

		return model;
	}

	@GetMapping("/novo")
	public ModelAndView novoPedido(@AuthenticationPrincipal Usuario usuarioLogado) {
		ModelAndView model = new ModelAndView("pedidounidade/form-pedidounidade");

		usuarioLogado.setPedido(new PedidoUnidade());

		model.addObject("pedido", usuarioLogado.getPedido());
		model.addObject("user", usuarioLogado);
		model.addObject("produtos", this.produtoService.buscarTodos());
		model.addObject("produtoSelecao", new ProdutoModal());

		return model;
	}

	/**
	 * Remove apenas da memoria o produto de um pedido.
	 * @param id
	 * @param usuarioLogado
	 * @return
	 */
	@GetMapping("/remover/{id}")
	public ModelAndView removerProdutoPedido(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {
		Long idPedido = usuarioLogado.getPedido().getId();
		ModelAndView model = new ModelAndView("redirect:/pedidosunidade/editar/"+(idPedido == null ? "0" : idPedido));

		for (ProdutoUnidade p : usuarioLogado.getPedido().getProdutos()) {
			if (p.getProduto().getId() == id) {
				usuarioLogado.getPedido().getProdutos().remove(p);
				break;
			}
		}

		return model;
	}

	/**
	 * Remove o pedido como um todo.
	 * @param id
	 * @param usuarioLogado
	 * @return
	 */
	@PostMapping("/excluir/{id}")
	public ModelAndView removerPedido(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {
		this.pedidoUnidadeService.deletarPorId(id);
		return form(usuarioLogado);
	}

	@PostMapping("/produto")
	public ModelAndView adicionarProduto(ProdutoModal selecao, @AuthenticationPrincipal Usuario usuarioLogado) {
		Long idPedido = usuarioLogado.getPedido().getId();
		ModelAndView model = new ModelAndView("redirect:/pedidosunidade/editar/"+(idPedido == null ? "0" : idPedido));
		if (selecao.getQuantidade() > 0) {
			Produto produto = this.produtoService.buscarPorId(selecao.getId());

			ProdutoUnidade ps = new ProdutoUnidade(produto, selecao.getQuantidade());

			boolean atualizado = false;
			for(ProdutoUnidade pu:usuarioLogado.getPedido().getProdutos()) {	
				if(pu.getProduto().getId().equals(ps.getProduto().getId())) {
					// achei o cara dentro da lista, atualiza a quantidade
					pu.setQuantidade(pu.getQuantidade() + ps.getQuantidade());
					atualizado = true;
					break;
				}
			}
			if (!atualizado) {
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

		return model;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {
		ModelAndView model = new ModelAndView("pedidounidade/form-pedidounidade");
		PedidoUnidade pedidoUnidade = null;
		if(id > 0 && usuarioLogado.getPedido() == null) {
			pedidoUnidade = this.pedidoUnidadeService.buscarPorId(id);
			usuarioLogado.setPedido(pedidoUnidade);
		}else {
			pedidoUnidade = usuarioLogado.getPedido();
		}

		usuarioLogado.getPedido().atualizarValor();

		model.addObject("pedido", pedidoUnidade);
		model.addObject("user", usuarioLogado);
		model.addObject("produtos", this.produtoService.buscarTodos());
		model.addObject("produtoSelecao", new ProdutoModal());

		return model;
	}

	@PostMapping
	public ModelAndView cadastrar(PedidoUnidade pedidoUnidade, @AuthenticationPrincipal Usuario usuarioLogado, RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView("redirect:/pedidosunidade");
		model.addObject("user", usuarioLogado);
		attributes.addFlashAttribute("user", usuarioLogado);
		PedidoUnidade pedido = usuarioLogado.getPedido();
		try {

			pedido.setUsuario(usuarioLogado);
			pedido.setStatus("ABERTO");
			pedido.setDataPedido(Calendar.getInstance());
			pedido.setUnidade(usuarioLogado.getUnidade());
			ArrayList<ProdutoUnidade> produtoUnidades = new ArrayList<>();

			// registrar os produtos e quantidade
			for (ProdutoUnidade p : pedido.getProdutos()) {
				System.out.println("Salvando produto "+p.getId()+" "+p.getProduto().getId()+" "+p.getProduto().getNome());
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

	@PostMapping("/finalizar")
	public ModelAndView finalizarPedido(PedidoUnidade pedidoUnidade, @AuthenticationPrincipal Usuario usuarioLogado, RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView("redirect:/pedidosunidade");

		try {
			PedidoUnidade pedido = this.pedidoUnidadeService.buscarPorId(pedidoUnidade.getId());
			if(usuarioLogado.getUnidade().getPedidoEspecial()) {
				pedido.setFormaEntrega(pedidoUnidade.getFormaEntrega());
				pedido.setCodigoRastreio(pedidoUnidade.getCodigoRastreio());
				pedido.setStatus("ENVIADO");
				msg = new FormMensagem(TipoMensagem.SUCESSO).addMensagem("Pedido número " + pedidoUnidade.getId() + " enviado com sucesso");
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
				msg = new FormMensagem(TipoMensagem.SUCESSO).addMensagem("Pedido número " + pedidoUnidade.getId() + " finalizado com sucesso");
			}
			this.pedidoUnidadeService.salvarOuAtualizar(pedido);
			
		} catch (Exception e) {
			System.out.println("Erro " + e);
			msg = new FormMensagem(TipoMensagem.ERRO).addMensagem("Não foi possivel atualizar o pedido");
		}
		attributes.addFlashAttribute("msg", msg);
		return model;
	}
}
