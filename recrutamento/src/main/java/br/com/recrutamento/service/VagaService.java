package br.com.recrutamento.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.recrutamento.controller.dto.VagaDTO;
import br.com.recrutamento.model.Usuario;
import br.com.recrutamento.model.Vaga;
import br.com.recrutamento.repository.UsuarioRepository;
import br.com.recrutamento.repository.VagaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class VagaService {

	@Autowired
	private VagaRepository vagaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public VagaDTO save(VagaDTO vagaDTO) {
		
		Usuario responsavel = usuarioRepository.findById(vagaDTO.responsavelId())
				.orElseThrow(() -> new EntityNotFoundException("Responsável não encontrado"));
		
		var vaga = vagaDTO.toEntity();
		vaga.setResponsavel(responsavel);
		
		vaga = vagaRepository.save(vaga);
		return new VagaDTO(vaga);
	}

	@Transactional
	public VagaDTO update(Integer id, VagaDTO vagaDTO) {
		Optional<Vaga> vagaOptional = vagaRepository.findById(id);
		if (vagaOptional.isPresent()) {
			
			Usuario responsavel = usuarioRepository.findById(vagaDTO.responsavelId())
					.orElseThrow(() -> new EntityNotFoundException("Responsável não encontrado"));
			
			var vaga = vagaDTO.toEntity();
			vaga.setResponsavel(responsavel);
			
			vaga = vagaRepository.save(vaga);
			return new VagaDTO(vaga);
		} else {
			throw new EntityNotFoundException("Vaga não encontrada");
		}
	}

	@Transactional
	public void deleteById(Integer id) {
		vagaRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<VagaDTO> findAll() {
		List<Vaga> vagas = vagaRepository.findAll();
		return vagas.stream().map(VagaDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public VagaDTO findById(Integer id) {
		Optional<Vaga> vagaOptional = vagaRepository.findById(id);
		return vagaOptional.map(VagaDTO::new).orElseThrow(() -> {
			throw new EntityNotFoundException("Vaga não encontrada");
		});
	}
}
