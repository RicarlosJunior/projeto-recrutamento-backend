package br.com.recrutamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void enviarEmail(String destinatario, String assunto, String corpo) throws MessagingException {
		MimeMessage mensagem = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mensagem, true);

		helper.setFrom("ricarlosjr5@gmail.com");
		helper.setTo(destinatario);
		helper.setSubject(assunto);
		helper.setText(corpo, true); // O segundo parâmetro "true" define que o corpo do e-mail será em HTML
		
		javaMailSender.send(mensagem);
	}
}
