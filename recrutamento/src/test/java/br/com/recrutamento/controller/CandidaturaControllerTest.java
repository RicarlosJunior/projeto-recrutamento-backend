package br.com.recrutamento.controller;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import br.com.recrutamento.service.CandidaturaService;

@SpringBootTest
@AutoConfigureMockMvc
public class CandidaturaControllerTest {

	
	 	@Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private CandidaturaService candidaturaService;
	   
	 
	    @Test
	    @WithMockUser(username = "colaborador", roles = "COLABORADOR") //Simula um usuario autenticado
	    void testCandidatar() throws Exception {
	        Integer usuarioId = 1;
	        Integer vagaId = 1;

	        
	        doNothing().when(candidaturaService).candidatar(usuarioId, vagaId);  
	        mockMvc.perform(post("/candidatar/{vagaId}", vagaId)
	                .param("usuarioId", usuarioId.toString()))  // O Execulta a requisicao
	                .andExpect(status().isOk())  // Espera um status HTTP 200
	                .andExpect(content().string("Candidatura realizada com sucesso.")); 
	    }
	    
	
}
