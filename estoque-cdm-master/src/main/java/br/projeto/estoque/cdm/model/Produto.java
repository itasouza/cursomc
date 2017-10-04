/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.model;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    // categoria integer NOT NULL
    @NotNull
    @Column(columnDefinition = "integer")
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
//    @NotNull
//    @Column(columnDefinition = "boolean")
//    private Boolean profissional = false;
//
//    // preco numeric(14,2) NOT NULL,
    @NotNull
    @Column(columnDefinition = "numeric(14,2)")
    private Double preco;
//
//    // peso numeric(14,4) NOT NULL,
//    @NotNull
//    @Column(columnDefinition = "numeric(14,4)")
//    private Double peso;
//
//    // cubagem numeric(14,4) NOT NULL,
//    @NotNull
//    @Column(columnDefinition = "numeric(14,4)")
//    private Double cubagem;
//
//    // pontos_unilevel numeric(14,2) NOT NULL,
//    @NotNull
//    @Column(columnDefinition = "numeric(14,2)")
//    private Double pontos_unilevel;
//
//    //  publicar boolean NOT NULL DEFAULT false,
//    @NotNull
//    @Column(columnDefinition = "boolean")
//    private Boolean publicar = false;
//
//    @Column(columnDefinition = "oid")
//    private Long imagem;
//
//    // alterado_por integer NOT NULL,
//    @NotNull
//    @Column(columnDefinition = "integer")
//    private Usuario alterado_por;
//
//    // alterado_em timestamp without time zone NOT NULL,
////    @NotNull
////    @Column(columnDefinition = "timestamp without time zone")
////    private Calendar alterado_em;
//
//    // limite_desconto numeric(5,2),
//    @Column(columnDefinition = "numeric(5,2)")
//    private Double limite_desconto;

    public Produto() {
    }

    public Long getId() {
        return id;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
