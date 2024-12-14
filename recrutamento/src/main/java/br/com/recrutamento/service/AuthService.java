package br.com.recrutamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.recrutamento.controller.dto.AcessDTO;
import br.com.recrutamento.controller.dto.AuthenticationDTO;
import br.com.recrutamento.security.jwt.JwtUtils;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticatioManager;

	@Autowired
	private JwtUtils jwtUtils;

	public AcessDTO login(AuthenticationDTO authenticationDTO) {

		try {
			// Cria mecanismo de credencial para o spring
			UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(
					authenticationDTO.getEmail(), authenticationDTO.getSenha());

			// Prepara mecanismo para autenticacao
			Authentication authentication = authenticatioManager.authenticate(userAuth);

			// Busca usuario logado
			UserDetailsImpl userAuthenticate = (UserDetailsImpl) authentication.getPrincipal();

			String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);

	
			AcessDTO accessDto = new AcessDTO();
			accessDto.setId(userAuthenticate.getId());
			accessDto.setNome(userAuthenticate.getName());
			accessDto.setEmail(userAuthenticate.getEmail());
			accessDto.setTipo(userAuthenticate.getTipo());
			accessDto.setToken(token);

			return accessDto;

		} catch (BadCredentialsException e) {
		}
		return new AcessDTO();
	}
}
