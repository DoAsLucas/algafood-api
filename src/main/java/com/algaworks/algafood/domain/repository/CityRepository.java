package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.City;

public interface CityRepository {
	List<City> listar();
	City buscar(Long id);
	City salvar(City city);
	void remover(City city);
}
