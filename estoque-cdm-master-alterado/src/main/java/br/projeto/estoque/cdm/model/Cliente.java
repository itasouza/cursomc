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
@Entity
public class Cliente implements Serializable {

    @Id
    @NotNull
    @Column(name = "id_cliente", columnDefinition = "serial")
    Long id;

    @NotNull
    @Column(name = "id_cadastro", columnDefinition = "integer")
    Long cadastro;

    @NotNull
    @Column(name = "nome_cliente ", columnDefinition = "character varying(100)")
    String nome;

    @NotNull
    @Column(name = "tipo_documento", columnDefinition = "character varying(10)")
    String tipoDocumento;

    @NotNull
    @Column(name = "num_documento", columnDefinition = "character varying(20)")
    String numDocumento;

    @NotNull
    @Column(columnDefinition = "character varying(150)")
    String email;

    @Column(columnDefinition = "date")
    String dt_nascimento;

    @Column(columnDefinition = "character varying(1)")
    String genero;

    @Column(columnDefinition = "character varying(3)")
    String ddd;

    @Column(columnDefinition = "character varying(10)")
    String telefone;

    @NotNull
    @Column(columnDefinition = "character varying(100)")
    String endereco;

    @NotNull
    @Column(columnDefinition = "character varying(10)")
    String numero;

    @NotNull
    @Column(columnDefinition = "character varying(50)")
    String bairro;

    @NotNull
    @Column(columnDefinition = "character varying(9)")
    String cep;

    @Column(name = "estado")
    String uf;

    @NotNull
    @Column(columnDefinition = "character varying(7)")
    String cidade;

    @Column(columnDefinition = "character varying(15)")
    String tipo_endereco_entrega;

    @Column(columnDefinition = "character varying(1)")
    String orientacao_entregador;

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCadastro() {
        return cadastro;
    }

    public void setCadastro(Long cadastro) {
        this.cadastro = cadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(String dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTipo_endereco_entrega() {
        return tipo_endereco_entrega;
    }

    public void setTipo_endereco_entrega(String tipo_endereco_entrega) {
        this.tipo_endereco_entrega = tipo_endereco_entrega;
    }

    public String getOrientacao_entregador() {
        return orientacao_entregador;
    }

    public void setOrientacao_entregador(String orientacao_entregador) {
        this.orientacao_entregador = orientacao_entregador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
