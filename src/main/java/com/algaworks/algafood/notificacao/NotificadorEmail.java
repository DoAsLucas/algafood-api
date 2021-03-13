package com.algaworks.algafood.notificacao;

import com.algaworks.algafood.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

	@Autowired
	private NotificadorProperties properties;

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.println("host " + properties.getHostServidor());
		System.out.println("porta " + properties.getPortaServidor());

		System.out.printf("Notificando %s através do email %s: %s\n",
						cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
