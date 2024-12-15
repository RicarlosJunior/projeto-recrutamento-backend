package br.com.recrutamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.recrutamento.exception.LoginException;
import br.com.recrutamento.model.Usuario;
import br.com.recrutamento.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, LoginException {
		Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new LoginException("Usuário não encontrado."));
		return UserDetailsImpl.build(usuario);
	}
}
