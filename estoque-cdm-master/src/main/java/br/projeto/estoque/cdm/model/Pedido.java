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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 *
 */
@Entity(name = "pedidos")
public class Pedido implements Serializable {

    @Id
    @NotNull
    @Column(name = "numero_pedido", columnDefinition = "integer")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cliente", columnDefinition = "integer")
    Cliente cliente;

    @NotNull
    @Column(name = "id_tipo_pedido", columnDefinition = "integer")
    Integer tipoPedido;

//    @Column(columnDefinition = "bigint")
    @ManyToOne
    @JoinColumn(name = "id_cdm", columnDefinition = "integer")
    Unidade unidade;

    @NotNull
    @Column(name = "dt_pedido", columnDefinition = "timestamp without time zone")
    String dataPedido;

    @Column(name = "dt_baixa_pedido", columnDefinition = "timestamp without time zone")
    String dataBaixaPedido;

    @Enumerated(EnumType.STRING)
    StatusPedido situacao;

    @NotNull
    @Column(name = "valor_total", columnDefinition = "numeric(10,2)")
    Double valorTotal;

    @Column(name = "faixa_desconto", columnDefinition = "numeric(7,4)")
    Double faixaDesconto;

    @Column(name = "valor_desconto", columnDefinition = "numeric(10,2)")
    Double valorDesconto;

    @NotNull
    @Column(name = "valor_final_pedido", columnDefinition = "numeric(10,2)")
    Double valorFinal;

    @NotNull
    @Column(name = "flg_cobrar_frete")
    Boolean cobrarFrete;

    @NotNull
    @Column(name = "valor_frete", columnDefinition = "numeric(10,2)")
    Double valorFrete;

    @NotNull
    @Column(name = "servico_entrega", columnDefinition = "character varying(1)")
    String servicoEntrega;

    @NotNull
    @Column(name = "forma_entrega", columnDefinition = "character varying(1)")
    String formaEnrega;

    @Column(name = "codigo_rastreio", columnDefinition = "character varying(50)")
    String codigoRastreio;

    @Column(name = "id_ecommerce", columnDefinition = "integer")
    Integer idEcommerce;

    @Column(name = "endereco_alt", columnDefinition = "text")
    String endereco;

    @Enumerated(EnumType.STRING)
    StatusPedido status;

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(Integer tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getDataBaixaPedido() {
        return dataBaixaPedido;
    }

    public void setDataBaixaPedido(String dataBaixaPedido) {
        this.dataBaixaPedido = dataBaixaPedido;
    }

    public StatusPedido getSituacao() {
        return situacao;
    }

    public void setSituacao(StatusPedido situacao) {
        this.situacao = situacao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getFaixaDesconto() {
        return faixaDesconto;
    }

    public void setFaixaDesconto(Double faixaDesconto) {
        this.faixaDesconto = faixaDesconto;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Boolean getCobrarFrete() {
        return cobrarFrete;
    }

    public void setCobrarFrete(Boolean cobrarFrete) {
        this.cobrarFrete = cobrarFrete;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public String getServicoEntrega() {
        return servicoEntrega;
    }

    public void setServicoEntrega(String servicoEntrega) {
        this.servicoEntrega = servicoEntrega;
    }

    public String getFormaEnrega() {
        return formaEnrega;
    }

    public void setFormaEnrega(String formaEnrega) {
        this.formaEnrega = formaEnrega;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    public Integer getIdEcommerce() {
        return idEcommerce;
    }

    public void setIdEcommerce(Integer idEcommerce) {
        this.idEcommerce = idEcommerce;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
