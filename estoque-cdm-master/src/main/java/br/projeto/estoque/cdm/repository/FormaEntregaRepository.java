package br.projeto.estoque.cdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.projeto.estoque.cdm.model.FormaEntrega;

@Repository
public interface FormaEntregaRepository extends JpaRepository<FormaEntrega, Integer>{

}
