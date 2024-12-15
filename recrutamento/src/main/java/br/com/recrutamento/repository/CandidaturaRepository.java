package br.com.recrutamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.recrutamento.model.Candidatura;
import br.com.recrutamento.model.enums.StatusCandidatura;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Integer> {
	
	@Query("SELECT c FROM Candidatura c WHERE c.usuario.id = :usuarioId")
    List<Candidatura> findCandidaturasByUsuarioId(@Param("usuarioId") Integer usuarioId);

	
}
