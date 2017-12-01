/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.controller;

import br.projeto.estoque.cdm.mensagem.FormMensagem;
import br.projeto.estoque.cdm.mensagem.TipoMensagem;
import br.projeto.estoque.cdm.model.Produto;
import br.projeto.estoque.cdm.model.Usuario;
import br.projeto.estoque.cdm.service.CategoriaProdutoService;
import br.projeto.estoque.cdm.service.ProdutoService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/produto")
public class ProdutoController {

    int paginacao = 50;

    FormMensagem msg;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    CategoriaProdutoService categoriaProdutoService;

    @GetMapping(value = {"", "/", "/{pagina}"})
    public ModelAndView form(@PathVariable(value = "pagina", required = false) Integer pagina,
            Produto produto, @AuthenticationPrincipal Usuario usuarioLogado) {

        ModelAndView model = new ModelAndView("produto/form-produto");

        long registros = this.produtoService.contarRegistros();
        ArrayList<Integer> pgn = new ArrayList<Integer>();
        int j = 1;
        for (int i = 0; i < registros; i += paginacao) {
            pgn.add(j);
            j++;
        }
        PageRequest page = null;
        if (pagina == null) {
            page = new PageRequest(0, paginacao);
        } else {
            page = new PageRequest(pagina, paginacao);
        }

        List<Produto> produtos = new ArrayList<>();
        Page<Produto> produtosPagina = this.produtoService.buscarPaginacao(page);
        produtosPagina.forEach(p -> {
            produtos.add(p);
        });
        model.addObject("paginas", pgn);
        model.addObject("produto", produto);
        model.addObject("produtos", produtos);
        model.addObject("categorias", this.categoriaProdutoService.buscarTodos());
        model.addObject("user", usuarioLogado);

        return model;
    }

    @PostMapping
    public ModelAndView salvar(@Valid Produto produto, RedirectAttributes attributes) {

        try {
            this.produtoService.salvarOuAtualizar(produto);
            msg = new FormMensagem(TipoMensagem.SUCESSO);
            msg.addMensagem("Produto registrado com sucesso");
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
// codigo ja existe
            msg = new FormMensagem(TipoMensagem.ERRO);
            msg.addMensagem("Código "+produto.getCodigo()+" já existe");
        } catch (Exception e) {
            System.out.println("Erro POST Produto: " + e);
            msg = new FormMensagem(TipoMensagem.ERRO);
            msg.addMensagem("Não foi possível registrar");
        }
        attributes.addFlashAttribute("msg", this.msg);
        return new ModelAndView("redirect:/produto");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {

        return this.form(0, this.produtoService.buscarPorId(id), usuarioLogado);
    }

    @DeleteMapping("/{id}")
    public ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {

        try {
            this.produtoService.deletarPorId(id);
            msg = new FormMensagem(TipoMensagem.SUCESSO);
            msg.addMensagem("Produto deletado com sucesso");
        } catch (Exception e) {
            System.out.println("Erro DELETE Produto: " + e);
            msg = new FormMensagem(TipoMensagem.ERRO);
            msg.addMensagem("Não foi possível deletar");
        }
        attributes.addFlashAttribute("msg", this.msg);
        return new ModelAndView("redirect:/produto");
    }
}
