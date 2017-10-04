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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * 
 */
@Entity(name = "tb_unidade")
public class Unidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcdm", columnDefinition = "serial")
    private Long id;

    @Column(columnDefinition = "character varying(200)")
    private String nome;

    @Column(columnDefinition = "character varying (30)")
    private String cnpj;

    @Column(columnDefinition = "character varying (200)")
    private String endereco;
    @Column(columnDefinition = "character varying (25)")
    private String numero;
    @Column(columnDefinition = "character varying (50)")
    private String cidade;
    @Column(columnDefinition = "character varying (30)")
    private String estado;
    @Column(columnDefinition = "character varying (25)")
    private String cep;
    @Column(columnDefinition = "character varying (200)")
    private String contato;
    @Column(columnDefinition = "character varying (20)")
    private String telefone;
    @Column(columnDefinition = "character varying (50)")
    private String email;
    
    
    @NotNull
    private Boolean ativo;

    @Column(name = "atende_pedido_especial")
    private Boolean pedidoEspecial = false;

    public Unidade() {
    }

    public Boolean getPedidoEspecial() {
        return pedidoEspecial;
    }

    public void setPedidoEspecial(Boolean pedidoEspecial) {
        this.pedidoEspecial = pedidoEspecial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final Unidade other = (Unidade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
