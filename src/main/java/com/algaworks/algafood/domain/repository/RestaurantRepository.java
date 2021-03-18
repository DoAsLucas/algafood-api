package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Restaurant;

public interface RestaurantRepository {
	
	List<Restaurant> listar();
	Restaurant buscar(Long id);
	Restaurant salvar(Restaurant restaurant);
	void remover(Restaurant restaurant);
}
