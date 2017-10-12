/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.controller;

import br.projeto.estoque.cdm.mensagem.FormMensagem;
import br.projeto.estoque.cdm.mensagem.TipoMensagem;
import br.projeto.estoque.cdm.model.Unidade;
import br.projeto.estoque.cdm.model.Usuario;
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
@RequestMapping("/unidade")
public class UnidadeController {
    
    FormMensagem msg = null;
    
    @Autowired
    UnidadeService unidadeService;
    
    @GetMapping
    public ModelAndView form(Unidade cdm,@AuthenticationPrincipal Usuario usuarioLogado) {
        ModelAndView model = new ModelAndView("unidade/form-unidade");
        
        model.addObject("unidade", cdm);
        model.addObject("unidades", this.unidadeService.buscarTodos());
        model.addObject("user", usuarioLogado);
        return model;
    }
    
    @PostMapping
    public ModelAndView salvar(Unidade unidade, RedirectAttributes attributes) {
        ModelAndView model = new ModelAndView("redirect:/unidade");
        
        try {
            this.unidadeService.salvarOuAtualizar(unidade);
            msg = new FormMensagem(TipoMensagem.SUCESSO).addMensagem("Unidade " + unidade.getNome() + " salva com sucesso");
        } catch (Exception e) {
            msg = new FormMensagem(TipoMensagem.ERRO).addMensagem("Não foi possível salvar a unidade");
        }
        attributes.addFlashAttribute("msg", msg);
        return model;
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {
        
        return this.form(this.unidadeService.buscarPorId(id), usuarioLogado);
    }
    
    @DeleteMapping("/{id}")
    public ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {
        
        try {
            this.unidadeService.deletarPorId(id);
            msg = new FormMensagem(TipoMensagem.SUCESSO);
            msg.addMensagem("Unidade deletado com sucesso");
        } catch (Exception e) {
            msg = new FormMensagem(TipoMensagem.ERRO);
            msg.addMensagem("Não foi possível deletar a unidade");
            msg.addMensagem("Verifique se possui algum registro vinculado");
        }
        attributes.addFlashAttribute("msg", this.msg);
        return new ModelAndView("redirect:/unidade");
    }
    
}
