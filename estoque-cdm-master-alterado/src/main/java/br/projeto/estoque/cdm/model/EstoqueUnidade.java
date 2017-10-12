/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 *
 */
@Entity(name = "tb_estoque_cdm")
public class EstoqueUnidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idestoque")
    private Long id;

    @ManyToOne
    Produto produto;

    @ManyToOne
    @NotNull
    Unidade unidade;

    @Column(name = "qtdestoquefisico")
    @NotNull
    Integer estoqueFisico;

    @Column(name = "estoqueminimo")
    @NotNull
    Integer estoqueMinimo;

    @Transient
    String msgPerda;

    public EstoqueUnidade() {
    }

    public EstoqueUnidade(Produto produto, Unidade unidade, Integer estoqueFisico, Integer estoqueMinimo) {
        this.produto = produto;
        this.unidade = unidade;
        this.estoqueFisico = estoqueFisico;
        this.estoqueMinimo = estoqueMinimo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getMsgPerda() {
        return msgPerda;
    }

    public void setMsgPerda(String msgPerda) {
        this.msgPerda = msgPerda;
    }

    public Integer getEstoqueFisico() {
        return estoqueFisico;
    }

    public void setEstoqueFisico(Integer estoqueFisico) {
        this.estoqueFisico = estoqueFisico;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final EstoqueUnidade other = (EstoqueUnidade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
