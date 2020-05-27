package com.thiagofarias.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.thiagofarias.cursomc.domain.Cliente;
import com.thiagofarias.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
