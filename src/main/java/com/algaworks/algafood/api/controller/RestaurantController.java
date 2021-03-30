package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.service.RestaurantService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	RestaurantService restaurantService;

	@Autowired
	KitchenRepository kitchenRepository;

	@GetMapping
	public List<Restaurant> list() {
		return restaurantRepository.list();
	}
	
	@GetMapping("/{restaurantId}")
	public ResponseEntity<Restaurant> get(@PathVariable Long restaurantId) {
		Restaurant restaurant =  restaurantRepository.get(restaurantId);

		if (restaurant != null) {
			return ResponseEntity.ok(restaurant);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
		try {
			restaurant = restaurantService.save(restaurant);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(restaurant);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{restaurantId}")
	public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
		try {
			Restaurant currentRestaurant = restaurantRepository.get(restaurantId);
			if (currentRestaurant == null) {
				return ResponseEntity.notFound().build();
			}

			Kitchen kitchen = kitchenRepository.get(restaurant.getKitchen().getId());
			if (kitchen == null) {
				throw new EntityNotFoundException(String.format(
						"Kitchen ID %d not found.", restaurant.getKitchen().getId()));
			}

			BeanUtils.copyProperties(restaurant, currentRestaurant, "id");
			restaurantRepository.save(currentRestaurant);

			currentRestaurant.setKitchen(kitchen);

			return ResponseEntity.ok(currentRestaurant);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{restaurantId}")
	public ResponseEntity<Restaurant> delete(@PathVariable Long restaurantId) {
		try {
			restaurantService.remove(restaurantId);
			return ResponseEntity.noContent().build();

		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
