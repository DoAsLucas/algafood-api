package com.algaworks.algafood.jpa;

import java.util.List;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaKitchenMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

			CadastroKitchen cadastroKitchen = applicationContext.getBean(CadastroKitchen.class);
			List<Kitchen> kitchenList = cadastroKitchen.listar();

			for (Kitchen kitchen : kitchenList) {
				System.out.println(kitchen.getName());
			}
	}
}
