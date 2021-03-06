package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.repository.StateRepository;
import com.algaworks.algafood.domain.service.CityService;

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
@RequestMapping(value = "/cities")
public class CityController {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	CityService cityService;

	@Autowired
	StateRepository stateRepository;

	@GetMapping
	public List<City> listar(){
		return cityRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> save(@RequestBody City city) {
		try {
			City createdCity = cityService.save(city);
			return ResponseEntity.ok().body(createdCity);

		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
	}

	@PutMapping("/{cityId}")
	public ResponseEntity<?> update(@PathVariable Long cityId, @RequestBody City city) {
		try{

			Optional<City> currentCity = cityRepository.findById(cityId);
	
			if (currentCity.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
	
			stateRepository.findById(city.getState().getId())
				.orElseThrow(() -> new EntityNotFoundException(
					String.format("State ID %d not found.", city.getState().getId())));
	
			BeanUtils.copyProperties(city, currentCity.get(), "id");
			City createdCity = cityService.save(currentCity.get());
	
			return ResponseEntity.ok(createdCity);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{cityId}")
	public ResponseEntity<City> delete(@PathVariable Long cityId) {
		try {
			cityService.delete(cityId);
			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
