package com.algaworks.algafood.jpa;

import java.util.List;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaKitchenMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

			KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);
			List<Kitchen> kitchenList = kitchenRepository.list();

			for (Kitchen kitchen : kitchenList) {
				System.out.println(kitchen.getName());
			}
	}
}
