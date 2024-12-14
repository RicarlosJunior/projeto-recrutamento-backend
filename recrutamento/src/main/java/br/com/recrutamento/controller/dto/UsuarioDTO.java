package br.com.recrutamento.controller.dto;

import br.com.recrutamento.model.Usuario;
import br.com.recrutamento.model.enums.TipoUsuario;

public record UsuarioDTO(Integer id, String nome,String email, String senha,String tipoUsuario) {

	public UsuarioDTO(Usuario entity) {
		this(entity.getId(), 
				entity.getNome(), 
				entity.getEmail(), 
				entity.getSenha(),
				entity.getTipoUsuario().name());
	}

	public Usuario toEntity() {
		Usuario usuario = new Usuario();
		usuario.setId(this.id);
		usuario.setNome(this.nome);
		usuario.setEmail(this.email);
		usuario.setSenha(this.senha);
		usuario.setTipoUsuario(TipoUsuario.valueOf(this.tipoUsuario));
		return usuario;
	}
	

}
