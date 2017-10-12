/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.service;

import br.projeto.estoque.cdm.model.RegistroPerda;
import br.projeto.estoque.cdm.repository.RegistroPerdaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 
 */
@Service
public class RegistroPerdaService implements Services<RegistroPerda> {

    @Autowired
    RegistroPerdaRepository repository;

    @Override
    public RegistroPerda buscarPorId(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public List<RegistroPerda> buscarTodos() {
        return this.repository.findAll();
    }

    @Override
    public RegistroPerda salvarOuAtualizar(RegistroPerda obj) {
        return this.repository.save(obj);
    }

    @Override
    public void deletarPorId(Long id) {
        this.repository.delete(id);
    }

    @Override
    public void deletar(RegistroPerda obj) {
        this.repository.delete(obj);
    }

}
