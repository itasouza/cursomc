/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.api.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import java.util.Date;

/**
 *
 * @author Administrador
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerObject {
    
    private Integer numeroPedido;
    
    private Integer tipoPedido;
    
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    private Date dtPedido;
    
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    private Date dtVencimento;
    
    private Integer idCdm;
    
    private String status;
    
    private Integer idEnvioUnidade;
    
    private Integer idFormaEntrega;
    
    private String nomeEntrega;
    
    private String ordemColetaEntrega;
    
    private String rgEntrega;
    
    private String notaFiscal;
    
    private ClienteObject cliente;
    
    private ItemObject[] itens;

    public Integer getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Integer getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(Integer tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public Integer getIdCdm() {
        return idCdm;
    }

    public void setIdCdm(Integer idCdm) {
        this.idCdm = idCdm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdEnvioUnidade() {
        return idEnvioUnidade;
    }

    public void setIdEnvioUnidade(Integer idEnvioUnidade) {
        this.idEnvioUnidade = idEnvioUnidade;
    }

    public Integer getIdFormaEntrega() {
        return idFormaEntrega;
    }

    public void setIdFormaEntrega(Integer idFormaEntrega) {
        this.idFormaEntrega = idFormaEntrega;
    }

    public String getNomeEntrega() {
        return nomeEntrega;
    }

    public void setNomeEntrega(String nomeEntrega) {
        this.nomeEntrega = nomeEntrega;
    }

    public String getOrdemColetaEntrega() {
        return ordemColetaEntrega;
    }

    public void setOrdemColetaEntrega(String ordemColetaEntrega) {
        this.ordemColetaEntrega = ordemColetaEntrega;
    }

    public String getRgEntrega() {
        return rgEntrega;
    }

    public void setRgEntrega(String rgEntrega) {
        this.rgEntrega = rgEntrega;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public ClienteObject getCliente() {
        return cliente;
    }

    public void setCliente(ClienteObject cliente) {
        this.cliente = cliente;
    }

    public ItemObject[] getItens() {
        return itens;
    }

    public void setItens(ItemObject[] itens) {
        this.itens = itens;
    }
    
    
    
}
