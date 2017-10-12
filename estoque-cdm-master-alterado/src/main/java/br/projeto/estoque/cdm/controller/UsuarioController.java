/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.controller;

import br.projeto.estoque.cdm.mensagem.FormMensagem;
import br.projeto.estoque.cdm.mensagem.TipoMensagem;
import br.projeto.estoque.cdm.model.Usuario;
import br.projeto.estoque.cdm.service.PerfilAcessoService;
import br.projeto.estoque.cdm.service.UnidadeService;
import br.projeto.estoque.cdm.service.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {

    FormMensagem msg;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PerfilAcessoService perfilAcessoService;

    @Autowired
    UnidadeService unidadeService;

    @GetMapping
    public ModelAndView form(Usuario usuario, @AuthenticationPrincipal Usuario usuarioLogado) {
        ModelAndView model = new ModelAndView("usuario/form-usuario");

        model.addObject("usuario", usuario);
        model.addObject("usuarios", this.usuarioService.buscarTodos());
        model.addObject("perfils", this.perfilAcessoService.buscarTodos());
        model.addObject("unidades", this.unidadeService.buscarTodos());
        model.addObject("user", usuarioLogado);

        return model;
    }

    @PostMapping
    public ModelAndView salvar(@Valid Usuario usuario, RedirectAttributes attributes) {

        try {
            this.usuarioService.salvarOuAtualizar(usuario);
            msg = new FormMensagem(TipoMensagem.SUCESSO);
            msg.addMensagem("Usuario " + usuario.getNome() + " registrado com sucesso");
        } catch (Exception e) {
            msg = new FormMensagem(TipoMensagem.ERRO);
            msg.addMensagem("Não foi possível registrar");
        }
        attributes.addFlashAttribute("msg", this.msg);
        return new ModelAndView("redirect:/usuario");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {

        return this.form(this.usuarioService.buscarPorId(id), usuarioLogado);
    }

    @DeleteMapping("/{id}")
    public ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {

        try {
            this.usuarioService.deletarPorId(id);
            msg = new FormMensagem(TipoMensagem.SUCESSO);
            msg.addMensagem("Usuario deletado com sucesso");
        } catch (Exception e) {
            msg = new FormMensagem(TipoMensagem.ERRO);
            msg.addMensagem("Não foi possível deletar");
        }
        attributes.addFlashAttribute("msg", this.msg);
        return new ModelAndView("redirect:/usuario");
    }
}
