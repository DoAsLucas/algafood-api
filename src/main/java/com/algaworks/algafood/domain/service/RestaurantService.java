package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
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
		kitchenRepository.findById(kitchenId)
			.orElseThrow(() -> new EntityNotFoundException(
				String.format("Kitchen ID %d not found.", kitchenId)));

		Restaurant createdRestaurant = restaurantRepository.save(restaurant);
		return createdRestaurant;
	}

	public void remove(Long restaurantId) {
		try {
			restaurantRepository.deleteById(restaurantId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("There is no restaurant register with ID %d", restaurantId));

		}
	}
}
