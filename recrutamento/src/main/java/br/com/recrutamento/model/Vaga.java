package br.com.recrutamento.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.recrutamento.model.enums.StatusVaga;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

@Entity
public class Vaga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @Column(nullable = false, length = 150)
	private String titulo;
	
	@Column(nullable = true, columnDefinition = "TEXT")
	private String descricao;
	
	@ElementCollection
	private List<String> requisitos;
	
	@ManyToOne
    @JoinColumn(name = "responsavel_id")
	@JsonIgnore
    private Usuario responsavel;
	
    @OneToMany(mappedBy = "vaga", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private List<Candidatura> candidaturas;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private StatusVaga statusVaga;

    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<String> getRequisitos() {
		return requisitos;
	}
	public void setRequisitos(List<String> requisitos) {
		this.requisitos = requisitos;
	}
	public Usuario getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
	public List<Candidatura> getCandidaturas() {
		return candidaturas;
	}
	public void setCandidaturas(List<Candidatura> candidaturas) {
		this.candidaturas = candidaturas;
	}

	public StatusVaga getStatusVaga() {
		return statusVaga;
	}
	public void setStatusVaga(StatusVaga statusVaga) {
		this.statusVaga = statusVaga;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vaga other = (Vaga) obj;
		return Objects.equals(id, other.id);
	}
}
