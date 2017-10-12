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
public class FormMensagem implements Mensagem {

    private final ArrayList<String> conteudo;

    private final TipoMensagem tipo;

    private String classe;

    private String icone;

    public FormMensagem(TipoMensagem tipo) {
        this.conteudo = new ArrayList<>();
        this.tipo = tipo;
        switch (tipo) {
            case ERRO:
                this.classe = "danger";
                this.icone = "error_outline";
                break;
            case SUCESSO:
                this.classe = "success";
                this.icone = "error_outline";
                break;
            case ATENCAO:
                this.classe = "card-panel orange darken-1";
                this.icone = "error_outline";
                break;
            default:
                this.classe = "card-panel blue darken-1";
                this.icone = "error_outline";
                break;
        }

    }

    public FormMensagem addMensagem(String texto) {
        this.conteudo.add(texto);
        return this;
    }

    @Override
    public String getClasse() {
        return this.classe;
    }

    @Override
    public TipoMensagem getTipo() {
        return this.tipo;
    }

    @Override
    public ArrayList<String> getConteudo() {
        return this.conteudo;
    }

    @Override
    public String getIcone() {
        return this.icone;
    }

}
