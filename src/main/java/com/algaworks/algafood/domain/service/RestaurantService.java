package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private KitchenRepository kitchenRepository;

	public Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenRepository.get(kitchenId);

		if (kitchen == null) {
			throw new EntityNotFoundException(
				String.format("Kitchen ID %d not found.", kitchenId));
		}
		restaurant.setKitchen(kitchen);
		return restaurantRepository.save(restaurant);
	}

	public void remove(Long restaurantId) {
		try {
			restaurantRepository.remove(restaurantId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("There is no restaurant register with ID %d", restaurantId));

		}
	}
}
