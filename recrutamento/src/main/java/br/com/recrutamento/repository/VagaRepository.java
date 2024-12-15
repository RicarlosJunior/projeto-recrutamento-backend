package br.com.recrutamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.recrutamento.model.Vaga;


@Repository
public interface VagaRepository extends JpaRepository<Vaga, Integer> {
	
	@Query("SELECT v FROM Vaga v JOIN v.requisitos r WHERE LOWER(r) LIKE LOWER(CONCAT('%', :requisito, '%'))")
	List<Vaga> findByRequisitosContainingIgnoreCase(@Param("requisito") String requisito);


	
}
