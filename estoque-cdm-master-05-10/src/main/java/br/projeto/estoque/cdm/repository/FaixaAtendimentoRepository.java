/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.repository;

import br.projeto.estoque.cdm.model.EstoqueUnidade;
import br.projeto.estoque.cdm.model.FaixaAtendimento;
import br.projeto.estoque.cdm.model.Unidade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * 
 */
@Repository
public interface FaixaAtendimentoRepository extends JpaRepository<FaixaAtendimento, Long> {

    public List<FaixaAtendimento> findByCepFinalGreaterThanEqualAndCepInicialLessThanEqual(String cepFinal, String cepInicial);

    public List<EstoqueUnidade> findByUnidade(Unidade unidade);

    // SELECT * from tb_produto_unidade OFFSET 5  LIMIT 2
//    @Query("SELECT u FROM tb_faixa_atendimento u offset = :inicio limit = :limite")
//    public List<FaixaAtendimento> findLimit(@Param("inicio") Integer inicio, @Param("limite") Integer limite);

}
