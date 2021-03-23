package com.algaworks.algafood.jpa;

import java.util.List;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaCityMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

			CityRepository cityRepository = applicationContext.getBean(CityRepository.class);
			List<City> cityList = cityRepository.listar();

			for (City city : cityList) {
				System.out.printf("Cidade %s \nEstado %s \n---------- \n",
						city.getName(), city.getState().getName());
			}
	}
}
