/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 *
 */
@Entity(name = "itens_pedido")
public class ItensPedido implements Serializable {

//  id_item_pedido serial NOT NULL,    
    @Id
    @NotNull
    @Column(name = "id_pedido", columnDefinition = "serial")
    Long id;

//  numero_pedido integer NOT NULL
    @NotNull
    @Column(name = "numero_pedido", columnDefinition = "integer")
    Long numeroPedido;

//  item integer NOT NULL,
    @NotNull
    @Column(columnDefinition = "integer")
    Long item;

//  id_plano integer,
    @Column(name = "id_plano", columnDefinition = "integer")
    Integer idPlano;

//  descricao_item character varying(255) NOT NULL,
    @NotNull
    @Column(name = "descricao_item", columnDefinition = "character varying(255)")
    String descricaoItem;

//    valor_unitario numeric(10,2) NOT NULL,
    @NotNull
    @Column(name = "valor_unitario", columnDefinition = "numeric(10,2)")
    Double valorUnitario;

//  qtde numeric(5,2) NOT NULL,
    @NotNull
    @Column(name = "qtde", columnDefinition = "numeric(5,2)")
    Double qtde;

//          valor_total numeric(10,2) NOT NULL,
    @NotNull
    @Column(name = "valor_total", columnDefinition = "numeric(10,2)")
    Double valorTotal;

// pontos numeric(8,0) NOT NULL,
    @NotNull
    @Column(name = "pontos", columnDefinition = "numeric(8,0)")
    Double pontos;

//  id_produto integer,
    @Column(name = "id_produto", columnDefinition = "integer")
    Long idProduto;

    public ItensPedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
    }

    public Integer getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(Integer idPlano) {
        this.idPlano = idPlano;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getQtde() {
        return qtde;
    }

    public void setQtde(Double qtde) {
        this.qtde = qtde;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getPontos() {
        return pontos;
    }

    public void setPontos(Double pontos) {
        this.pontos = pontos;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final ItensPedido other = (ItensPedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
