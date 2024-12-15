package br.com.recrutamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handleGlobalException(Throwable e) {
		String mensagem = "Ocorreu um erro inesperado: ";
		logger.error(mensagem, e);
		return new ResponseEntity<>(mensagem + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(LoginException.class)
    public ResponseEntity<String> handleLoginException(LoginException loginException) {
        return new ResponseEntity<>(loginException.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(UsuarioException.class)
    public ResponseEntity<String> handleUsuarioException(UsuarioException usuarioException) {
        return new ResponseEntity<>(usuarioException.getMessage(), HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AuthorizationDeniedException ex) {
        return new ResponseEntity<>("Acesso negado. Você não tem permissão para acessar este recurso.", HttpStatus.UNAUTHORIZED);
    }
}
