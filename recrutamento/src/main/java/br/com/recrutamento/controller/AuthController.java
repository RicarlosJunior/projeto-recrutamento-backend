package br.com.recrutamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.recrutamento.controller.dto.AcessDTO;
import br.com.recrutamento.controller.dto.AuthenticationDTO;
import br.com.recrutamento.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/auth")
@CrossOrigin 
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping(value = "/login")
	@Operation(summary = "Login", description = "Recurso para autenticação", tags = "LOGIN")
	public ResponseEntity<AcessDTO> login(@RequestBody AuthenticationDTO authenticationDTO){
		return ResponseEntity.ok(authService.login(authenticationDTO));
	}
}