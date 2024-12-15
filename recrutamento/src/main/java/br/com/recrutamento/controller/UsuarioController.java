package br.com.recrutamento.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.recrutamento.controller.dto.UsuarioDTO;
import br.com.recrutamento.controller.dto.VagaDTO;
import br.com.recrutamento.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	@Operation(summary = "Criar Usuário", description = "Recurso que cria um novo usuário", tags = "USUARIO")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioDTO usuarioDTO) {
		
		var usuario = usuarioService.save(usuarioDTO);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(usuario.id())
				.toUri();
		
		return ResponseEntity.created(location).body(usuario);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar Usuário", description = "Recurso que atualiza um usuário", tags = "USUARIO")
	@PreAuthorize("hasAnyRole('ADMIN', 'COLABORADOR')")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
		usuarioService.update(id, usuarioDTO);
		return ResponseEntity.ok(usuarioDTO);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar Usuário", description = "Recurso que deleta um usuário", tags = "USUARIO")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		usuarioService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Consultar Usuario por Id", description = "Recurso que consulta um usuário por id", tags = "USUARIO")
	@PreAuthorize("hasAnyRole('ADMIN', 'COLABORADOR')")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) {
		var usuario = usuarioService.findById(id);
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping
	@Operation(summary = "Consultar todos os Usuários", description = "Recurso que consulta todos os usuários cadastrados", tags = "USUARIO")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		var usuarios = usuarioService.findAll();
		return ResponseEntity.ok(usuarios);
	}
	
}
