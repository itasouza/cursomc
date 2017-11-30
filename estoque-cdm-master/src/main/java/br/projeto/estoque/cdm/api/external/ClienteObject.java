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
public class ClienteObject {

    private Integer idCliente;
    private String nomeCliente;
    private String tipoDocumento;
    private String numDocumento;
    private String email;
    private String dtNascimento;//ano-mes-doa
    private String genero;
    private String ddd;
    private String telefone;
    private String endereco;
    private String numero;
    private String bairro;
    private String cep;    
    private CidadeObject cidade;    
    private String tipoEnderecoEntrega;
    private String orientacaoEntregador;    
    private CadastroObject cadastro;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public CidadeObject getCidade() {
        return cidade;
    }

    public void setCidade(CidadeObject cidade) {
        this.cidade = cidade;
    }

    public String getTipoEnderecoEntrega() {
        return tipoEnderecoEntrega;
    }

    public void setTipoEnderecoEntrega(String tipoEnderecoEntrega) {
        this.tipoEnderecoEntrega = tipoEnderecoEntrega;
    }

    public String getOrientacaoEntregador() {
        return orientacaoEntregador;
    }

    public void setOrientacaoEntregador(String orientacaoEntregador) {
        this.orientacaoEntregador = orientacaoEntregador;
    }

    public CadastroObject getCadastro() {
        return cadastro;
    }

    public void setCadastro(CadastroObject cadastro) {
        this.cadastro = cadastro;
    }
    
    

}
