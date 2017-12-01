/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 *
 */
@Entity(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    // id serial NOT NULL
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    // categoria integer NOT NULL
    @NotNull
//    @Column(columnDefinition = "integer")
    Long categoria;

//    // codigo character varying(10) NOT NULL,
    @NotNull
    @Column(columnDefinition = "character varying(10)")
    private String codigo;

//    // nome character varying(100) NOT NULL,
    @NotNull
    @Column(columnDefinition = "character varying(100)")
    private String nome;

    // descricao text,
    @NotNull
    @Column(columnDefinition = "text")
    private String descricao;

    // profissional boolean NOT NULL DEFAULT false,
    @NotNull
    @Column(columnDefinition = "boolean")
    private Boolean profissional = false;

    @NotNull
    @Column(columnDefinition = "publicar")
    private Boolean publicar = false;
//
//    // preco numeric(14,2) NOT NULL,
    @NotNull
    @Column(columnDefinition = "numeric(14,2)")
    private Double preco;

    // peso numeric(14,4) NOT NULL,
    @NotNull
    @Column(columnDefinition = "numeric(14,4)")
    private Double peso;

    //    // cubagem numeric(14,4) NOT NULL,
    @NotNull
    @Column(columnDefinition = "numeric(14,4)")
    private Double cubagem;

//    @NotNull
    @Column(columnDefinition = "numeric(14,2)", name = "preco_sdc")
    private Double precoSDC;

//    @NotNull
    @Column(columnDefinition = "numeric(14,2)", name = "preco_cdm")
    private Double precoCDM;

//    @NotNull
    @Column(columnDefinition = "numeric(14,2)", name = "preco_dlog")
    private Double precoDLOG;

    @NotNull
    private Integer qtdcaixa;

    @Column
    private Integer pontos_unilevel = 0;

    private Integer alterado_por = 22; // administrador do sistema
    private Timestamp alterado_em = new Timestamp(System.currentTimeMillis());

    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public Boolean getPublicar() {
        return publicar;
    }

    public void setPublicar(Boolean publicar) {
        this.publicar = publicar;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

    public Integer getPontos_unilevel() {
        return pontos_unilevel;
    }

    public void setPontos_unilevel(Integer pontos_unilevel) {
        this.pontos_unilevel = pontos_unilevel;
    }

    public Integer getAlterado_por() {
        return alterado_por;
    }

    public void setAlterado_por(Integer alterado_por) {
        this.alterado_por = alterado_por;
    }

    public Timestamp getAlterado_em() {
        return alterado_em;
    }

    public void setAlterado_em(Timestamp alterado_em) {
        this.alterado_em = alterado_em;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtdcaixa() {
        return qtdcaixa;
    }

    public void setQtdcaixa(Integer qtdcaixa) {
        this.qtdcaixa = qtdcaixa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoSDC() {
        return precoSDC;
    }

    public void setPrecoSDC(Double precoSDC) {
        this.precoSDC = precoSDC;
    }

    public Boolean getProfissional() {
        return profissional;
    }

    public void setProfissional(Boolean profissional) {
        this.profissional = profissional;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getCubagem() {
        return cubagem;
    }

    public void setCubagem(Double cubagem) {
        this.cubagem = cubagem;
    }

    public Double getPrecoCDM() {
        return precoCDM;
    }

    public void setPrecoCDM(Double precoCDM) {
        this.precoCDM = precoCDM;
    }

    public Double getPrecoDLOG() {
        return precoDLOG;
    }

    public void setPrecoDLOG(Double precoDLOG) {
        this.precoDLOG = precoDLOG;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
