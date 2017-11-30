/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.controller;

import br.projeto.estoque.cdm.mensagem.FormMensagem;
import br.projeto.estoque.cdm.mensagem.TipoMensagem;
import br.projeto.estoque.cdm.model.EstoqueUnidade;
import br.projeto.estoque.cdm.model.RegistroPerda;
import br.projeto.estoque.cdm.model.Usuario;
import br.projeto.estoque.cdm.service.EstoqueUnidadeService;
import br.projeto.estoque.cdm.service.ProdutoService;
import br.projeto.estoque.cdm.service.RegistroPerdaService;
import br.projeto.estoque.cdm.service.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/estoqueunidade")
public class EstoqueUnidadeController {

    FormMensagem msg = null;

    @Autowired
    EstoqueUnidadeService estoqueUnidadeService;

    @Autowired
    UnidadeService unidadeService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    RegistroPerdaService perdaService;

    @GetMapping
    public ModelAndView form(EstoqueUnidade estoque, @AuthenticationPrincipal Usuario usuarioLogado) {
        ModelAndView model = new ModelAndView("estoqueunidade/form-estoque");

        model.addObject("estoque", estoque);
        model.addObject("estoques", this.estoqueUnidadeService.buscarPorUnidade(usuarioLogado.getUnidade()));
        model.addObject("unidades", usuarioLogado.getUnidade());
        model.addObject("produtos", this.produtoService.buscarTodos());
        model.addObject("user", usuarioLogado);
        return model;
    }

    @PostMapping
    public ModelAndView salvar(EstoqueUnidade estoque, RedirectAttributes attributes) {
        ModelAndView model = new ModelAndView("redirect:/estoqueunidade");
        System.out.println("Salvando");
        try {
            EstoqueUnidade estoqueExiste = this.estoqueUnidadeService.buscarPorProdutoEUnidade(estoque.getProduto(), estoque.getUnidade());
                if(estoqueExiste != null && !estoqueExiste.getId().equals(estoque.getId())) {
                    msg = new FormMensagem(TipoMensagem.ERRO).addMensagem("Estoque já registrado para esta unidade");
                    attributes.addFlashAttribute("msg", msg);
                    return model;
                }
            
            if (estoque.getId() == null) {                
                System.out.println("Novo registro");
                estoque.setEstoqueFisico(0);
                estoque = this.estoqueUnidadeService.salvarOuAtualizar(estoque);
                msg = new FormMensagem(TipoMensagem.SUCESSO).addMensagem("Estoque #" + estoque.getId() + " salvo com sucesso");
            } else {
                System.out.println("Registro de perda");
                // registro de perda
                EstoqueUnidade eu = this.estoqueUnidadeService.buscarPorId(estoque.getId());
                
                if (estoque.getEstoqueFisico() > 0 && eu.getEstoqueFisico() < estoque.getEstoqueFisico()) {
                    System.out.println("Sem estoque para registrar perda");
                    // nao tem a quantidade para remover
                    msg = new FormMensagem(TipoMensagem.ERRO).addMensagem("Estoque insuficiente para registro de perda");
                    attributes.addFlashAttribute("msg", msg);
                    return model;
                }
                
                if(estoque.getEstoqueFisico() > 0) {
                    System.out.println("Tem estoque suficiente "+estoque.getProduto().getId());
                    // registrar perda
                    eu.setEstoqueFisico(eu.getEstoqueFisico() - estoque.getEstoqueFisico());                    
                    RegistroPerda perda = new RegistroPerda(estoque.getEstoqueFisico(), estoque.getMsgPerda(), estoque.getProduto());
                    // registrnado a perda
                    this.perdaService.salvarOuAtualizar(perda);
                }
                
                if(estoque.getEstoqueMinimo()==null && estoque.getEstoqueMinimo()<0) {
                    msg = new FormMensagem(TipoMensagem.ERRO).addMensagem("Estoque mínimo deve ser maior que zero");
                    attributes.addFlashAttribute("msg", msg);
                    return model;
                }
                eu.setEstoqueMinimo(estoque.getEstoqueMinimo());
                this.estoqueUnidadeService.salvarOuAtualizar(eu);
                
                msg = new FormMensagem(TipoMensagem.SUCESSO).addMensagem("Perda registrada com sucesso");
            }
        } catch (Exception e) {
            System.out.println("Erro " + e);
            msg = new FormMensagem(TipoMensagem.ERRO).addMensagem("Não foi possível salvar o estoque");
        }
        attributes.addFlashAttribute("msg", msg);
        return model;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {

        return this.form(this.estoqueUnidadeService.buscarPorId(id), usuarioLogado);
    }

    @DeleteMapping("/{id}")
    public ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {

        try {
            this.estoqueUnidadeService.deletarPorId(id);
            msg = new FormMensagem(TipoMensagem.SUCESSO);
            msg.addMensagem("Estoque deletado com sucesso");
        } catch (Exception e) {
            msg = new FormMensagem(TipoMensagem.ERRO);
            msg.addMensagem("Não foi possível deletar");
        }
        attributes.addFlashAttribute("msg", this.msg);
        return new ModelAndView("redirect:/estoqueunidade");
    }
}
