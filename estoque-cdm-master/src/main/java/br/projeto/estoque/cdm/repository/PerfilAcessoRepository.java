/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.repository;

import br.projeto.estoque.cdm.model.PerfilAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * 
 */
@Repository
public interface PerfilAcessoRepository extends JpaRepository<PerfilAcesso, Long> {

}
