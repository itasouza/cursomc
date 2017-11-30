/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.controller;

import br.projeto.estoque.cdm.mensagem.FormMensagem;
import br.projeto.estoque.cdm.mensagem.TipoMensagem;
import br.projeto.estoque.cdm.model.Cidade;
import br.projeto.estoque.cdm.model.Transportadora;
import br.projeto.estoque.cdm.model.Unidade;
import br.projeto.estoque.cdm.model.Usuario;
import br.projeto.estoque.cdm.service.CidadeService;
import br.projeto.estoque.cdm.service.TransportadoraService;
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
 */
@Controller
@RequestMapping("/cidade")
public class CidadeController {
    
    FormMensagem msg = null;
    
    @Autowired
    CidadeService cidadeService;
    
    @GetMapping
    public ModelAndView form(Cidade cidade, @AuthenticationPrincipal Usuario usuarioLogado) {
        ModelAndView model = new ModelAndView("cidade/form-cidade");
        
        model.addObject("cidade", cidade);
        model.addObject("cidades", this.cidadeService.buscarTodos());
        model.addObject("user", usuarioLogado);
        return model;
    }
    
    @PostMapping
    public ModelAndView salvar(Cidade cidade, RedirectAttributes attributes) {
        ModelAndView model = new ModelAndView("redirect:/cidade");
        
        try {
            this.cidadeService.salvarOuAtualizar(cidade);
            msg = new FormMensagem(TipoMensagem.SUCESSO).addMensagem("Cidade " + cidade.getNome() + " salva com sucesso");
        } catch (Exception e) {
            msg = new FormMensagem(TipoMensagem.ERRO).addMensagem("Não foi possível salvar a cidade");
        }
        attributes.addFlashAttribute("msg", msg);
        return model;
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {
        
        return this.form(this.cidadeService.buscarPorId(id), usuarioLogado);
    }
    
    @DeleteMapping("/{id}")
    public ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {
        
        try {
            this.cidadeService.deletarPorId(id);
            msg = new FormMensagem(TipoMensagem.SUCESSO);
            msg.addMensagem("Cidade removida com sucesso");
        } catch (Exception e) {
            msg = new FormMensagem(TipoMensagem.ERRO);
            msg.addMensagem("Não foi possível remover a cidade");
            msg.addMensagem("Verifique se possui algum registro vinculado");
        }
        attributes.addFlashAttribute("msg", this.msg);
        return new ModelAndView("redirect:/cidade");
    }
    
}
