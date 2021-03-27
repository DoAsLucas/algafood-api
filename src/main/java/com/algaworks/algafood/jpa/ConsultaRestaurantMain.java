package com.algaworks.algafood.jpa;

import java.util.List;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaRestaurantMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

			RestaurantRepository kitchenRepository = applicationContext.getBean(RestaurantRepository.class);
			List<Restaurant> restaurantList = kitchenRepository.list();

			for (Restaurant restaurant : restaurantList) {
				System.out.printf("Restaurante %s \nTaxa %s \n%s \n ---------- \n",
						restaurant.getName(), restaurant.getTaxaFrete(), restaurant.getKitchen().getName());
			}
	}
}
