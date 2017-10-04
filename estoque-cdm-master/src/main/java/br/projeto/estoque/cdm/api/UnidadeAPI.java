/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.api;

import br.projeto.estoque.cdm.model.FaixaAtendimento;
import br.projeto.estoque.cdm.model.Unidade;
import br.projeto.estoque.cdm.service.FaixaAtendimentoService;
import java.util.ArrayList;
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

    @GetMapping(value = "/consulta/{cep}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Unidade>> pesquisaUnidadeCEP(@PathVariable String cep) {

        cep = cep.replaceAll("-", "").replaceAll(".", "");
        List<FaixaAtendimento> faixa = new ArrayList<>();
        List<Unidade> unidades = new ArrayList<>();
        try {
            faixa = this.atendimentoService.filtarCep(cep);
            for (FaixaAtendimento at : faixa) {
                unidades.add(at.getUnidade());
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro na API Unidade. Erro " + e);
            return ResponseEntity.ok(new ArrayList<Unidade>());
        }
        return ResponseEntity.ok(unidades);

    }
}
