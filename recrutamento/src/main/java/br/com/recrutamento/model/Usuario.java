package br.com.recrutamento.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.recrutamento.model.enums.TipoUsuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(unique = true)		
	private String email;
	
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private TipoUsuario tipoUsuario;
	
	@OneToMany(mappedBy = "responsavel", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnore
    private List<Vaga> vagas;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
	@JsonIgnore
    private List<Candidatura> candidaturas;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public List<Candidatura> getCandidaturas() {
		return candidaturas;
	}
	public void setCandidaturas(List<Candidatura> candidaturas) {
		this.candidaturas = candidaturas;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
}
