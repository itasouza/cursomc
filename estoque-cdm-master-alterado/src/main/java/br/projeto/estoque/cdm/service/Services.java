/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.service;

import java.util.List;

/**
 *
 * 
 * @param <T> classe de implementacao
 */
public interface Services<T> {

    public T buscarPorId(Long id);

    public List<T> buscarTodos();

    public T salvarOuAtualizar(T obj);

    public void deletarPorId(Long id);

    public void deletar(T obj);

}
