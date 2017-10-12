/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.controller;

import br.projeto.estoque.cdm.mensagem.FormMensagem;
import br.projeto.estoque.cdm.mensagem.TipoMensagem;
import br.projeto.estoque.cdm.model.PerfilAcesso;
import br.projeto.estoque.cdm.model.Usuario;
import br.projeto.estoque.cdm.service.PerfilAcessoService;
import javax.validation.Valid;
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
@RequestMapping("/perfilAcesso")
public class PerfilAcessoController {

    FormMensagem msg = null;

    @Autowired
    PerfilAcessoService perfilAcessoService;

    @GetMapping
    public ModelAndView form(PerfilAcesso perfilAcesso, @AuthenticationPrincipal Usuario usuarioLogado) {
        ModelAndView model = new ModelAndView("perfilacesso/perfilacesso");

        model.addObject("perfil", perfilAcesso);
        model.addObject("user", usuarioLogado);
        try {
            model.addObject("perfils", this.perfilAcessoService.buscarTodos());
        } catch (Exception e) {
            msg = new FormMensagem(TipoMensagem.ERRO);
            msg.addMensagem("Não foi possível carregar os registros");
            model.addObject("msg", this.msg);
        }
        return model;
    }

    @PostMapping
    public ModelAndView salvar(@Valid PerfilAcesso perfilAcesso, RedirectAttributes attributes) {

        try {
            this.perfilAcessoService.salvarOuAtualizar(perfilAcesso);
            msg = new FormMensagem(TipoMensagem.SUCESSO);
            msg.addMensagem("Perfil " + perfilAcesso.getDescricao() + " registrado com sucesso");
        } catch (Exception e) {
            msg = new FormMensagem(TipoMensagem.ERRO);
            msg.addMensagem("Não foi possível registrar");
        }
        attributes.addFlashAttribute("msg", this.msg);
        return new ModelAndView("redirect:/perfilAcesso");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {

        return this.form(this.perfilAcessoService.buscarPorId(id), usuarioLogado);
    }

    @DeleteMapping("/{id}")
    public ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {

        try {
            this.perfilAcessoService.deletarPorId(id);
            msg = new FormMensagem(TipoMensagem.SUCESSO);
            msg.addMensagem("Perfil deletado com sucesso");
        } catch (Exception e) {
            msg = new FormMensagem(TipoMensagem.ERRO);
            msg.addMensagem("Não foi possível deletar");
        }
        attributes.addFlashAttribute("msg", this.msg);
        return new ModelAndView("redirect:/perfilAcesso");
    }
}
