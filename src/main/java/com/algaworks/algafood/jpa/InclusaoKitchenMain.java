package com.algaworks.algafood.jpa;

import java.util.List;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoKitchenMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

			CadastroKitchen cadastroKitchen = applicationContext.getBean(CadastroKitchen.class);

			Kitchen cozinha1 = new Kitchen();
			cozinha1.setName("Brasileira");

			Kitchen cozinha2 = new Kitchen();
			cozinha2.setName("Japonesa");

			cozinha1 = cadastroKitchen.adicionar(cozinha1);
			cozinha2 = cadastroKitchen.adicionar(cozinha2);

			System.out.printf("%d - %s\n", cozinha1.getId(), cozinha1.getName());
			System.out.printf("%d - %s\n", cozinha2.getId(), cozinha2.getName());
		}
}
