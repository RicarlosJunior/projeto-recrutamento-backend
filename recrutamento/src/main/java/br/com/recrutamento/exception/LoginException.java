package br.com.recrutamento.exception;

public class LoginException  extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public LoginException(String mensagemException) {
        super(mensagemException);
    }
}
