package br.com.recrutamento.model;

import java.util.Objects;

import br.com.recrutamento.model.enums.StatusCandidatura;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Candidatura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "vaga_id")
	private Vaga vaga;
	
	@Enumerated(EnumType.STRING)
	private StatusCandidatura statusCandidatura;
	
	@Column(columnDefinition = "TEXT")
	private String devolutiva;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Vaga getVaga() {
		return vaga;
	}
	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
	public StatusCandidatura getStatusCandidatura() {
		return statusCandidatura;
	}
	public void setStatusCandidatura(StatusCandidatura statusCandidatura) {
		this.statusCandidatura = statusCandidatura;
	}
	
	public String getDevolutiva() {
		return devolutiva;
	}
	public void setDevolutiva(String devolutiva) {
		this.devolutiva = devolutiva;
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
		Candidatura other = (Candidatura) obj;
		return Objects.equals(id, other.id);
	}
}
