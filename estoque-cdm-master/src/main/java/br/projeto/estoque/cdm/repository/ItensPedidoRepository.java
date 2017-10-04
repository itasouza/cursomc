/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.repository;

import br.projeto.estoque.cdm.model.ItensPedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * 
 */
@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Long> {

    public List<ItensPedido> findByNumeroPedido(Long id);

}
