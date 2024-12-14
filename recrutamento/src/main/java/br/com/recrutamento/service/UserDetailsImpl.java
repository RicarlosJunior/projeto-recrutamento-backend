package br.com.recrutamento.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.recrutamento.model.Usuario;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String tipo;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Integer id, String name, String email, String password, String tipo,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.tipo = tipo;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(Usuario usuario) {

		Collection<GrantedAuthority> authorities = new ArrayList<>();

		if (usuario.getTipoUsuario().name().equals("ADMIN")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_COLABORADOR"));
		}
		return new UserDetailsImpl(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(),
				usuario.getTipoUsuario().name(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
