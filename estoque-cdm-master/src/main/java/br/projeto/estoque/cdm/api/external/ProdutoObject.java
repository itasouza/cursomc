/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.api.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Administrador
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoObject {

    private Integer id;
    private String codigo;
    private String categoria;
    private String nome;
    private String descricao;
    private Boolean profissional;
    private Double preco;
    private Double precoSDC;
    private Double precoDistribuidor;
    private Double valorReservaBonus;
    private Double valorReservaBonusSdc;
    private Double peso;
    private Integer pontosUnilevel;
    private Integer cubagem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getProfissional() {
        return profissional;
    }

    public void setProfissional(Boolean profissional) {
        this.profissional = profissional;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getPrecoSDC() {
        return precoSDC;
    }

    public void setPrecoSDC(Double precoSDC) {
        this.precoSDC = precoSDC;
    }

    public Double getPrecoDistribuidor() {
        return precoDistribuidor;
    }

    public void setPrecoDistribuidor(Double precoDistribuidor) {
        this.precoDistribuidor = precoDistribuidor;
    }

    public Double getValorReservaBonus() {
        return valorReservaBonus;
    }

    public void setValorReservaBonus(Double valorReservaBonus) {
        this.valorReservaBonus = valorReservaBonus;
    }

    public Double getValorReservaBonusSdc() {
        return valorReservaBonusSdc;
    }

    public void setValorReservaBonusSdc(Double valorReservaBonusSdc) {
        this.valorReservaBonusSdc = valorReservaBonusSdc;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getPontosUnilevel() {
        return pontosUnilevel;
    }

    public void setPontosUnilevel(Integer pontosUnilevel) {
        this.pontosUnilevel = pontosUnilevel;
    }

    public Integer getCubagem() {
        return cubagem;
    }

    public void setCubagem(Integer cubagem) {
        this.cubagem = cubagem;
    }

}
