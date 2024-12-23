package br.com.recrutamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.recrutamento.controller.dto.CandidaturaDTO;
import br.com.recrutamento.controller.dto.VagaDTO;
import br.com.recrutamento.service.CandidaturaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/candidaturas")
@CrossOrigin 
public class CandidaturaController {
	
	@Autowired
	private CandidaturaService candidaturaService;
	
	@PostMapping("/candidatar/{vagaId}")
	@Operation(summary = "Candidatar a Vaga", description = "Recurso para se candidatar a uma vaga", tags = "CANDIDATURA")
	@PreAuthorize("hasAnyRole('ADMIN', 'COLABORADOR')")
    public ResponseEntity<String> candidatar(@RequestParam Integer usuarioId, @PathVariable Integer vagaId) {
        try {
            candidaturaService.candidatar(usuarioId, vagaId);
            return ResponseEntity.ok("Candidatura realizada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao candidatar: " + e.getMessage());
        }
    }
	
	@GetMapping("/consultar/{usuarioId}")
	@Operation(summary = "Consultar Candidaturas por Usuario", description = "Recurso que consulta candidaturas por usuario ", tags = "CANDIDATURA")
	@PreAuthorize("hasAnyRole('ADMIN', 'COLABORADOR')")
	public ResponseEntity<List<CandidaturaDTO>> findCandidaturasByUsuarioId(@PathVariable Integer usuarioId) {
		var candidaturas = candidaturaService.findCandidaturasByUsuarioId(usuarioId);
		return ResponseEntity.ok(candidaturas);
	}
	
	
	@GetMapping
	@Operation(summary = "Consultar todas as Candidaturas", description = "Recurso que consulta todas as candidaturas cadastradas", tags = "CANDIDATURA")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<CandidaturaDTO>> findAll() {
		var candidaturas = candidaturaService.findAll();
		return ResponseEntity.ok(candidaturas);
	}
	
	@PutMapping("/aprovar/{id}")
	@Operation(summary = "Aprovar Candidatura", description = "Recurso que aprova uma candidatura", tags = "CANDIDATURA")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<CandidaturaDTO> aprovar(@PathVariable Integer id, @RequestBody CandidaturaDTO candidaturaDTO) {
		candidaturaService.aprovar(id, candidaturaDTO);
		return ResponseEntity.ok(candidaturaDTO);
	}
	
	
	@PutMapping("/reprovar/{id}")
	@Operation(summary = "Reprovar Candidatura", description = "Recurso que reprova uma candidatura", tags = "CANDIDATURA")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<CandidaturaDTO> reprovar(@PathVariable Integer id, @RequestBody CandidaturaDTO candidaturaDTO) {
		candidaturaService.reprovar(id, candidaturaDTO);
		return ResponseEntity.ok(candidaturaDTO);
	}
	
}
