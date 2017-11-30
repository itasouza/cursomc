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
public class EstadoObject {
    
    private String sigla;
    
    private String nome;
    
    private PaisObject pais;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PaisObject getPais() {
        return pais;
    }

    public void setPais(PaisObject pais) {
        this.pais = pais;
    }
    
    
    
}
