package br.com.recrutamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.recrutamento.model.Usuario;
import br.com.recrutamento.model.Vaga;
import jakarta.mail.MessagingException;

@Service
public class NotificacaoService {
	
	
	@Autowired
    private EmailService emailService;
	
	
	public void enviarNotificacaoCandidato(Usuario candidato, Vaga vaga) throws MessagingException {
		String assunto =  "Confirmação de Inscrição";
		String mensagem = String.format(
    	    "<html><body><h2>Confirmação de Inscrição</h2><p>Olá %s,</p><p>Obrigado por se inscrever na vaga de %s! Em breve entraremos em contato.</p></body></html>",
    	     candidato.getNome(), vaga.getTitulo()
    	 );
		
		 emailService.enviarEmail(candidato.getEmail(), assunto, mensagem);
		 
    }

    public void enviarNotificacaoResponsavel(Usuario responsavel, Vaga vaga, Usuario candidato) throws MessagingException {
    	String assunto = "Nova Candidatura Recebida";
    	String mensagem = String.format(
        	    "<html><body><h2>Nova Candidatura Recebida</h2><p>Você recebeu uma nova candidatura para a vaga de %s!</p><p>Nome do Candidato: %s</p><p>Email do Candidato: %s</p></body></html>",
        	    vaga.getTitulo(), candidato.getNome(), candidato.getEmail()
        	);
    	 emailService.enviarEmail(responsavel.getEmail(), assunto, mensagem);
    }
}
