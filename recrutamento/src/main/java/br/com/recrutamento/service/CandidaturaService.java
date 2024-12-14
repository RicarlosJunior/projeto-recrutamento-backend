package br.com.recrutamento.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.recrutamento.controller.dto.CandidaturaDTO;
import br.com.recrutamento.controller.dto.VagaDTO;
import br.com.recrutamento.model.Candidatura;
import br.com.recrutamento.model.Vaga;
import br.com.recrutamento.model.enums.StatusCandidatura;
import br.com.recrutamento.repository.CandidaturaRepository;
import br.com.recrutamento.repository.UsuarioRepository;
import br.com.recrutamento.repository.VagaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CandidaturaService {

	@Autowired
	private CandidaturaRepository candidaturaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private VagaRepository vagaRepository;

	@Autowired
	private NotificacaoService notificacaoService;
	
	@Transactional
	public void candidatar(Integer usuarioId, Integer vagaId) throws Exception {

		var cadidato = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
		var vaga = vagaRepository.findById(vagaId)
				.orElseThrow(() -> new EntityNotFoundException("Vaga não encontrada"));

		Candidatura candidatura = new Candidatura();
		candidatura.setUsuario(cadidato);
		candidatura.setVaga(vaga);
		candidatura.setStatusCandidatura(StatusCandidatura.PENDENTE);

		candidaturaRepository.save(candidatura);
		
		notificacaoService.enviarNotificacaoCandidato(cadidato, vaga);
		notificacaoService.enviarNotificacaoResponsavel(vaga.getResponsavel(), vaga, cadidato);
		
	}
	
	@Transactional(readOnly = true)
	public List<CandidaturaDTO> findCandidaturasByUsuarioId(Integer usuarioId) {
		List<Candidatura> candidaturas = candidaturaRepository.findCandidaturasByUsuarioId(usuarioId);
		return candidaturas.stream().map(CandidaturaDTO::new).collect(Collectors.toList());
	}
	
}