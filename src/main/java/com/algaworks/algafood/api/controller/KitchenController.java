package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.service.KitchenService;

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
@RequestMapping(value = "/kitchens")
public class KitchenController {

	@Autowired
	KitchenRepository kitchenRepository;

	@Autowired
	KitchenService kitchenService;

	@GetMapping
	public List<Kitchen> list() {
		return kitchenRepository.list();
	}

	@GetMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> get(@PathVariable Long kitchenId) {
		Kitchen kitchen = kitchenRepository.get(kitchenId);

		if (kitchen != null) {
			return ResponseEntity.ok(kitchen);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen save(@RequestBody Kitchen kitchen) {
		return kitchenService.save(kitchen);
	}

	@PutMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
		Kitchen currentKitchen = kitchenRepository.get(kitchenId);

		if (currentKitchen == null) {
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(kitchen, currentKitchen, "id");
		kitchenService.save(currentKitchen);

		return ResponseEntity.ok(currentKitchen);
	}

	@DeleteMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> delete(@PathVariable Long kitchenId) {
		try {
			kitchenService.delete(kitchenId);
			return ResponseEntity.noContent().build();

		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
