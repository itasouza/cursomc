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
public class PaisObject {
    
    private String codigo;
    
    private String nome;
    
    private String idioma;
    
    private String ddi;

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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }
    
    
}
