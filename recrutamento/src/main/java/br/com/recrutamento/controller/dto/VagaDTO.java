package br.com.recrutamento.controller.dto;

import java.util.List;

import br.com.recrutamento.model.Vaga;

public record VagaDTO(Integer id, String titulo, String descricao, List<String> requisitos, Integer responsavelId) {

	public VagaDTO(Vaga entity) {
		this(entity.getId(), 
				entity.getTitulo(), 
				entity.getDescricao(), 
				entity.getRequisitos(),
				entity.getResponsavel().getId());
	}

	public Vaga toEntity() {
		Vaga vaga = new Vaga();
		vaga.setId(this.id);
		vaga.setTitulo(this.titulo);
		vaga.setDescricao(this.descricao);
		vaga.setRequisitos(this.requisitos);
		return vaga;
	}
}
