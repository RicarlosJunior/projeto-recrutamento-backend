package br.com.recrutamento.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.recrutamento.controller.dto.UsuarioDTO;
import br.com.recrutamento.controller.dto.VagaDTO;
import br.com.recrutamento.model.Usuario;
import br.com.recrutamento.model.Vaga;
import br.com.recrutamento.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	 private PasswordEncoder passwordEncoder;
	
	@Transactional
	public UsuarioDTO save(UsuarioDTO usuarioDTO) {	
		var usuario = usuarioDTO.toEntity();
		usuario.setSenha(passwordEncoder.encode(usuarioDTO.senha()));
		usuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
	}

	@Transactional
	public UsuarioDTO update(Integer id, UsuarioDTO usuarioDTO) {
		
		usuarioRepository.findById(id)
					  .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
		
		var usuario = usuarioDTO.toEntity();
		usuario.setSenha(passwordEncoder.encode(usuarioDTO.senha()));
		usuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
	}

	@Transactional
	public void deleteById(Integer id) {
		usuarioRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll() {
		List<Usuario> vagas = usuarioRepository.findAll();
		return vagas.stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}


	@Transactional(readOnly = true)
	public UsuarioDTO findById(Integer id) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		return usuarioOptional.map(UsuarioDTO::new).orElseThrow(() -> {
			throw new EntityNotFoundException("Usuário não encontrado");
		});
	}
	
}
