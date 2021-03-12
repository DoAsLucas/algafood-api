package com.algaworks.algafood;

import com.algaworks.algafood.notificacao.NotificadorEmail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgaConfig {
	
	@Bean
	public NotificadorEmail notificacadorEmail() {
		NotificadorEmail notificador = new NotificadorEmail("smtp.algamail.com.br");
		notificador.setCaixaAlta(Boolean.TRUE);
		return notificador;
	}
}
