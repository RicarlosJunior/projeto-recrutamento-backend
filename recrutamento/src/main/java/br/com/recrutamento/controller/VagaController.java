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

import br.com.recrutamento.controller.dto.VagaDTO;
import br.com.recrutamento.service.VagaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/vagas")
@CrossOrigin
public class VagaController {

	@Autowired
	private VagaService vagaService;

	@PostMapping
	@Operation(summary = "Criar Vaga", description = "Recurso que cria uma nova vaga", tags = "VAGA")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<VagaDTO> save(@RequestBody VagaDTO vagaDTO) {
		
		var vaga = vagaService.save(vagaDTO);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(vaga.id())
				.toUri();
		
		return ResponseEntity.created(location).body(vaga);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar Vaga", description = "Recurso que atualiza uma vaga", tags = "VAGA")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<VagaDTO> update(@PathVariable Integer id, @RequestBody VagaDTO vagaDTO) {
		vagaService.update(id, vagaDTO);
		return ResponseEntity.ok(vagaDTO);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar Vaga", description = "Recurso que deleta uma vaga", tags = "VAGA")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		vagaService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	@Operation(summary = "Consultar todas as Vagas", description = "Recurso que consulta todas as vagas cadastradas", tags = "VAGA")
	@PreAuthorize("hasAnyRole('ADMIN', 'COLABORADOR')")
	public ResponseEntity<List<VagaDTO>> findAll() {
		var vagas = vagaService.findAll();
		return ResponseEntity.ok(vagas);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Consultar por Id", description = "Recurso que consulta uma vaga por id", tags = "VAGA")
	@PreAuthorize("hasAnyRole('ADMIN', 'COLABORADOR')")
	public ResponseEntity<VagaDTO> findById(@PathVariable Integer id) {
		var vaga = vagaService.findById(id);
		return ResponseEntity.ok(vaga);
	}
}
