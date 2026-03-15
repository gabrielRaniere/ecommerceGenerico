package com.ecommerce.ecommerceGenerico.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Email {
	
	@Value("${email.enviador}")
	private String emailEnviador;
	
	private final JavaMailSender mailSender;

	public Email(JavaMailSender mailSender) {
	  this.mailSender = mailSender;
	}
	
	public void enviaEmail(String emailDestinatario, String nomeDestinatario, Double valorTotal) {
		SimpleMailMessage mensagem = new SimpleMailMessage();
		mensagem.setFrom(emailEnviador);
		mensagem.setTo(emailDestinatario);
		mensagem.setSubject("CONFIRMAÇÃO DE PEDIDO");
		mensagem.setText(String.format("Olá %s, seu pedido já foi cadastrado no valor de %f",
				nomeDestinatario, valorTotal));
		
		mailSender.send(mensagem);
	}
}
