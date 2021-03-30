package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Restaurant;

public interface RestaurantRepository {
	
	List<Restaurant> list();
	Restaurant get(Long id);
	Restaurant save(Restaurant restaurant);
	void remove(Long restaurantId);
}
