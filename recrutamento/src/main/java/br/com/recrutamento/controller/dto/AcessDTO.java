package br.com.recrutamento.controller.dto;

public class AcessDTO {
	
	private Integer id;
	private String nome;
	private String email;
	private String tipo;
	private String token;

	public AcessDTO() {
		this.id = 0;
		this.nome = "";
		this.email = "";
		this.tipo = "";
		this.token = "";
	}

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
