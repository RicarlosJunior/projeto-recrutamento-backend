package br.com.recrutamento.exception;

public class UsuarioException  extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UsuarioException(String mensagemException) {
        super(mensagemException);
    }
}
