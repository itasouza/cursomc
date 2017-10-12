/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.service;

import br.projeto.estoque.cdm.model.PerfilAcesso;
import br.projeto.estoque.cdm.repository.PerfilAcessoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 
 */
@Service
public class PerfilAcessoService implements Services<PerfilAcesso> {
    
    @Autowired
    PerfilAcessoRepository repository;
    
    @Override
    public PerfilAcesso buscarPorId(Long id) {
        return this.repository.findOne(id);
    }
    
    @Override
    public List<PerfilAcesso> buscarTodos() {
        return this.repository.findAll();
    }
    
    @Override
    public PerfilAcesso salvarOuAtualizar(PerfilAcesso obj) {
        return this.repository.save(obj);
    }
    
    @Override
    public void deletarPorId(Long id) {
        this.repository.delete(id);
    }
    
    @Override
    public void deletar(PerfilAcesso obj) {
        this.repository.delete(obj);
    }
    
}
