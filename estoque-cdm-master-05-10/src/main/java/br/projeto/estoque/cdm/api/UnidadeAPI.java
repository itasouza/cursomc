/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.api;

import br.projeto.estoque.cdm.model.FaixaAtendimento;
import br.projeto.estoque.cdm.model.Unidade;
import br.projeto.estoque.cdm.service.FaixaAtendimentoService;
import br.projeto.estoque.cdm.service.PedidoService;
import br.projeto.estoque.cdm.service.UnidadeService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 */
@RestController
@RequestMapping("/api/unidade")
public class UnidadeAPI {

    @Autowired
    FaixaAtendimentoService atendimentoService;

    @Autowired
    UnidadeService unidadeService;

    @Autowired
    PedidoService pedidoService;

    @GetMapping(value = "/consulta/{cep}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Unidade>> pesquisaUnidadeCEP(@PathVariable String cep) {
        String cepDesformatado = cep.replaceAll("-", "").replaceAll(" ", "");
        if (cepDesformatado.length() > 8 || cepDesformatado.contains(".")) {
            System.out.println("CEP invalido " + cep);
            return ResponseEntity.ok(new ArrayList<Unidade>());
        }
        List<FaixaAtendimento> faixa = new ArrayList<>();
        List<Unidade> unidades = new ArrayList<>();
        try {
            faixa = this.atendimentoService.filtarCep(cepDesformatado);
            for (FaixaAtendimento at : faixa) {
                unidades.add(at.getUnidade());
            }

            if (unidades.isEmpty()) {
                // buscar quem atende pedido especial
                List<Unidade> unidade = this.unidadeService.buscarAtendimentoEspecial();
                if (unidade != null) {
                    return ResponseEntity.ok(unidade);
                }
            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro na API Unidade. Erro " + e);
            return ResponseEntity.ok(new ArrayList<Unidade>());
        }
        this.pedidoService.registrarUnidadeEmNovosPedidos();
        return ResponseEntity.ok(unidades);

    }
}