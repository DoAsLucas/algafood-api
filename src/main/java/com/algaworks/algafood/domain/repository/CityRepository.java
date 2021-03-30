package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.City;

public interface CityRepository {
	List<City> list();
	City get(Long id);
	City save(City city);
	void remove(Long id);
}
