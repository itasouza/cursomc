/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.api;

import br.projeto.estoque.cdm.api.external.AnswerAuth;
import br.projeto.estoque.cdm.api.external.AnswerObject;
import br.projeto.estoque.cdm.api.external.RequestAuth;
import br.projeto.estoque.cdm.api.external.ResponseAuth;
import br.projeto.estoque.cdm.api.external.ResponseObject;
import br.projeto.estoque.cdm.model.EstoqueUnidade;
import br.projeto.estoque.cdm.model.FaixaAtendimento;
import br.projeto.estoque.cdm.model.ItensPedido;
import br.projeto.estoque.cdm.model.Pedido;
import br.projeto.estoque.cdm.model.PedidoUnidade;
import br.projeto.estoque.cdm.model.Produto;
import br.projeto.estoque.cdm.model.Unidade;
import br.projeto.estoque.cdm.repository.ItensPedidoRepository;
import br.projeto.estoque.cdm.service.EstoqueUnidadeService;
import br.projeto.estoque.cdm.service.FaixaAtendimentoService;
import br.projeto.estoque.cdm.service.PedidoService;
import br.projeto.estoque.cdm.service.UnidadeService;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    ItensPedidoRepository itensPedidoRepository;

    @Autowired
    EstoqueUnidadeService estoqueUnidadeService;

    @GetMapping(value = "/consulta/{cep}/{idPedido}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Unidade> pesquisaUnidadeCEP(@PathVariable String cep, @PathVariable Long idPedido) {
        String cepDesformatado = cep.replaceAll("-", "").replaceAll(" ", "");
        if (cepDesformatado.length() > 8 || cepDesformatado.contains(".")) {
            System.out.println("CEP invalido " + cep);
            return ResponseEntity.ok(new Unidade());
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
                if (unidade != null && (!unidade.isEmpty())) {
                    this.pedidoService.atualizarUnidade(idPedido, unidade.get(0));
                    return ResponseEntity.ok(unidade.get(0));
                }
            } else {
                // adicionar a unidade no pedido
                this.pedidoService.atualizarUnidade(idPedido, unidades.get(0));
            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro na API Unidade. Erro " + e);
            return ResponseEntity.ok(new Unidade());
        }

        return ResponseEntity.ok(unidades.get(0));

    }

    @GetMapping(value = "/consulta/{cep}/{idPedido}/{status}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Unidade> pesquisaUnidadeCEPStatus(@PathVariable String cep, @PathVariable Long idPedido, @PathVariable String status) {
        String cepDesformatado = cep.replaceAll("-", "").replaceAll(" ", "");
        if (cepDesformatado.length() > 8 || cepDesformatado.contains(".")) {
            System.out.println("CEP invalido " + cep);
            return ResponseEntity.ok(new Unidade());
        }

        Unidade unidadeSelecionada = null;

        try {
            List<Unidade> unidades = new ArrayList<>();
            List<FaixaAtendimento> faixas = this.atendimentoService.filtarCep(cepDesformatado);
            for (FaixaAtendimento at : faixas) {
                unidades.add(at.getUnidade());
            }

            if (idPedido != null && idPedido > 0) {
                try {
                    ResponseAuth auth = auth("sistemacdm", "qZrm4Rqk");
                    if (!auth.getSuccess()) {
                        throw new Exception(auth.getErrorMessage());
                    }
                    ResponseObject object = answer(auth.getAnswer().getToken(), "115873");
                    if (!object.getSuccess()) {
                        throw new Exception(object.getErrorMessage());
                    }
                    AnswerObject answer = object.getAnswer();
                    Pedido pedido = new Pedido();
//                    pedido.setId(answer.getNumeroPedido());
//                    pedido.setTipoPedido(answer.getTipoPedido());
//                    pedido.setDataPedido(answer.getDtPedido());
//                    Unidade unidade = unidadeService.buscarPorId(answer.getIdCdm());
//                    pedido.setUnidade(unidade);
//                    pedido.setStatus(answer.getStatus());
                    
                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }

            //encontrou unidade para o cep informado?
            if (!unidades.isEmpty()) {
                unidadeSelecionada = unidades.get(0);
                if (unidadeSelecionada != null && unidadeSelecionada.getId() != null) {
                    List<ItensPedido> itens = itensPedidoRepository.findByNumeroPedido(idPedido);
                    if (itens != null && !itens.isEmpty()) {
                        Boolean temEstoque = true;
                        for (ItensPedido ip : itens) {
                            Produto produto = new Produto();
                            produto.setId(ip.getIdProduto());
                            EstoqueUnidade estoqueUnidade = estoqueUnidadeService.buscarPorProdutoEUnidade(produto, unidadeSelecionada);
                            if (estoqueUnidade != null) {
                                if (estoqueUnidade.getEstoqueFisico() < ip.getQtde()) {
                                    temEstoque = false;
                                    System.out.println(String.format("Sem estoque suficiente de produto %s ", ip.getIdProduto()));
                                }
                            } else {
                                temEstoque = false;
                                System.out.println(String.format("Nenhum estoque encontrado de produto %s ", ip.getIdProduto()));
                            }
                        }
                        if (!temEstoque) {
                            unidadeSelecionada = null;
                        }
                    }

                }

                // buscar quem atende pedido especial se não tiver encontrado unidade ou não tiver estoque
                if (unidadeSelecionada == null) {
                    List<Unidade> unidadesEspeciais = this.unidadeService.buscarAtendimentoEspecial();
                    if (unidadesEspeciais != null && (!unidadesEspeciais.isEmpty())) {
                        unidadeSelecionada = unidadesEspeciais.get(0);
                    }
                }

                //atualizando pedido
                if (unidadeSelecionada != null) {
                    this.pedidoService.atualizarUnidadeStatus(idPedido, unidadeSelecionada, status);
                } else {
                    unidadeSelecionada = new Unidade();
                }
            }
            return ResponseEntity.ok(unidadeSelecionada);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro na API Unidade. Erro " + e);
            return ResponseEntity.ok(new Unidade());
        }

    }

    private static ResponseAuth auth(String username, String password) throws Exception {
        URI uri = new URI("http://hml.ezitus.com/matriz/services/auth/login");
        RestTemplate restTemplate = new RestTemplate();
        RequestAuth authRequest = new RequestAuth();
        authRequest.setUsername(username);
        authRequest.setPassword(password);
        ResponseAuth authResponse = restTemplate.postForObject(uri, authRequest, ResponseAuth.class);
        return authResponse;
    }

    private static ResponseObject answer(String token, String pedido) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("AuthToken", token);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        URI uri = new URI(String.format("http://hml.ezitus.com/matriz/services/pedidos/porNumero/%s", pedido));
        RestTemplate restTemplate = new RestTemplate();
        ///AnswerObject answerObject = restTemplate.getForEntity(uri, AnswerObject.class);
        ResponseEntity<ResponseObject> response = restTemplate.exchange(uri, HttpMethod.GET, request, ResponseObject.class);
        ResponseObject answerObject = response.getBody();
        return answerObject;
    }
}
