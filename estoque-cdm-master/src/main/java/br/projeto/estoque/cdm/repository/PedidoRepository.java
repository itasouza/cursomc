/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.repository;

import br.projeto.estoque.cdm.model.Pedido;
import br.projeto.estoque.cdm.model.Unidade;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE pedidos p SET p.unidade = :unidade WHERE p = :pedido")
    public Integer setUnidadeWhere(@Param("unidade") Unidade unidade, @Param("pedido") Pedido pedido);

    public List<Pedido> findByUnidade(Unidade unidade);

    @Modifying
    @Transactional
    @Query("UPDATE pedidos p SET p.status = :status WHERE p.id = :id")
    public void updateStatusWhereId(@Param("status") String status, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE pedidos p SET p.unidade = :unidade WHERE p.id = :id")
    public void updateUnidadeWhereId(@Param("unidade") Unidade get, @Param("id") Long idPedido);

    public Page<Pedido> findByUnidade(Unidade unidade, Pageable pageable);
    
    @Query("select p from pedidos p where p.unidade = :unidade and p.status in (:status)")
    public Page<Pedido> findByUnidadeAndStatus(@Param("unidade") Unidade unidade, @Param("status") List<String> status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE pedidos p SET p.unidade = :unidade, p.status = :status WHERE p.id = :id")
    public void updateUnidadeWhereId(@Param("unidade") Unidade get, @Param("id") Long idPedido, @Param("status") String status);
}
