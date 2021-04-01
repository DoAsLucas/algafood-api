package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
		return restaurantRepository.findAll();
	}
	
	@GetMapping("/{restaurantId}")
	public ResponseEntity<Restaurant> get(@PathVariable Long restaurantId) {
		Optional<Restaurant> restaurant =  restaurantRepository.findById(restaurantId);

		if (restaurant.isPresent()) {
			return ResponseEntity.ok(restaurant.get());
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
			Optional<Restaurant> currentRestaurant = restaurantRepository.findById(restaurantId);
			if (currentRestaurant.isEmpty()) {
				return ResponseEntity.notFound().build();
			}

			Kitchen kitchen = kitchenRepository.getOne(restaurant.getKitchen().getId());
			if (kitchen == null) {
				throw new EntityNotFoundException(String.format(
						"Kitchen ID %d not found.", restaurant.getKitchen().getId()));
			}

			BeanUtils.copyProperties(restaurant, currentRestaurant.get(), "id");
			Restaurant createdRestaurant = restaurantRepository.save(currentRestaurant.get());

			return ResponseEntity.ok(createdRestaurant);
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

	@PatchMapping("/{restaurantId}")
	public ResponseEntity<?> partialUpdate(@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields) {
		Optional<Restaurant> currentRestaurant = restaurantRepository.findById(restaurantId);

		if (currentRestaurant.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		merge(fields, currentRestaurant.get());

		return update(restaurantId, currentRestaurant.get());
	}

	private void merge(Map<String, Object> sourceFields, Restaurant targetRestaurant) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant sourceRestaurant = objectMapper.convertValue(sourceFields, Restaurant.class);

		sourceFields.forEach((propertyName, propertyValue) -> {
			Field field = ReflectionUtils.findField(Restaurant.class, propertyName);
			field.setAccessible(true);

			Object newValue = ReflectionUtils.getField(field, sourceRestaurant);

			ReflectionUtils.setField(field, targetRestaurant, newValue);
		});
	}
}
