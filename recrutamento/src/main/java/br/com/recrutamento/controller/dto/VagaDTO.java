package br.com.recrutamento.controller.dto;

import java.util.List;

import br.com.recrutamento.model.Vaga;
import br.com.recrutamento.model.enums.StatusVaga;

public record VagaDTO(Integer id, String titulo, String descricao, List<String> requisitos, Integer responsavelId, String statusVaga) {

	public VagaDTO(Vaga entity) {
		this(entity.getId(), 
				entity.getTitulo(), 
				entity.getDescricao(), 
				entity.getRequisitos(),
				entity.getResponsavel().getId(),
				entity.getStatusVaga().name());
	}

	public Vaga toEntity() {
		Vaga vaga = new Vaga();
		vaga.setId(this.id);
		vaga.setTitulo(this.titulo);
		vaga.setDescricao(this.descricao);
		vaga.setRequisitos(this.requisitos);
		vaga.setStatusVaga(StatusVaga.valueOf(this.statusVaga));
		return vaga;
	}
}
