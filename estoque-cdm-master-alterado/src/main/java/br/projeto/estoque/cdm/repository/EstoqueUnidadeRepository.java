/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.repository;

import br.projeto.estoque.cdm.model.EstoqueUnidade;
import br.projeto.estoque.cdm.model.Produto;
import br.projeto.estoque.cdm.model.Unidade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface EstoqueUnidadeRepository extends JpaRepository<EstoqueUnidade, Long> {

    public List<EstoqueUnidade> findByUnidade(Unidade unidade);

    public EstoqueUnidade findByProduto(Produto produto);
    
    public EstoqueUnidade findByProdutoAndUnidade(Produto produto, Unidade unidade);
}
