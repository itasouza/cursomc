/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.mensagem;

import java.util.ArrayList;

/**
 *
 * 
 */
public interface Mensagem {

    public String getClasse();

    public TipoMensagem getTipo();

    public ArrayList<String> getConteudo();
    
    public String getIcone();
}
