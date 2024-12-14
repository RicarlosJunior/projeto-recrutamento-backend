package br.com.recrutamento.controller.dto;

import br.com.recrutamento.model.Candidatura;
import br.com.recrutamento.model.Vaga;

public record CandidaturaDTO(Integer id, Vaga vaga, String statusCandidatura) {
	
	public CandidaturaDTO(Candidatura entity) {
		this(entity.getId(), 
			 entity.getVaga(), 
			 entity.getStatusCandidatura().name());
	}

	
	
}
