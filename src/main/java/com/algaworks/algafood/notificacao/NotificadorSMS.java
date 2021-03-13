package com.algaworks.algafood.notificacao;

import com.algaworks.algafood.model.Cliente;

import org.springframework.stereotype.Component;

@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorSMS implements Notificador {

		@Override
		public void notificar(Cliente cliente, String mensagem) {
			System.out.printf("Notificando %s através do telefone %s via SMS: %s\n",
					cliente.getNome(), cliente.getEmail(), mensagem);
		}
}
