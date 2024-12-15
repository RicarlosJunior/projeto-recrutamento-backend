package br.com.recrutamento.controller.dto;

import br.com.recrutamento.model.Candidatura;
import br.com.recrutamento.model.Usuario;
import br.com.recrutamento.model.Vaga;
import br.com.recrutamento.model.enums.StatusCandidatura;

public record CandidaturaDTO(Integer id, Vaga vaga, String statusCandidatura, Usuario usuario, String devolutiva) {
	
	public CandidaturaDTO(Candidatura entity) {
		this(entity.getId(), 
			 entity.getVaga(), 
			 entity.getStatusCandidatura().name(),
			 entity.getUsuario(),
			 entity.getDevolutiva());
	}

	public Candidatura toEntity() {
		Candidatura candidatura = new Candidatura();
		candidatura.setId(this.id);
		candidatura.setVaga(this.vaga);
		candidatura.setStatusCandidatura(StatusCandidatura.valueOf(this.statusCandidatura));
		candidatura.setUsuario(this.usuario);
		candidatura.setDevolutiva(this.devolutiva);
		return candidatura;
	}
	
	
}
