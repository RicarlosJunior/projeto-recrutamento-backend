package br.com.recrutamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.recrutamento.model.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Integer> {
}
