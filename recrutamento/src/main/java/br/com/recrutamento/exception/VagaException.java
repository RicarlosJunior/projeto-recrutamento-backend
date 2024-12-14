package br.com.recrutamento.exception;

public class VagaException  extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public VagaException(String mensagemException) {
        super(mensagemException);
    }
}
