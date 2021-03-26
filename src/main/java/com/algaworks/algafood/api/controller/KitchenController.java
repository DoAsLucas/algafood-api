package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {

	@Autowired
	KitchenRepository kitchenRepository;

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
	public Kitchen create(@RequestBody Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}

	@PutMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
		Kitchen currentKitchen = kitchenRepository.get(kitchenId);

		if (currentKitchen == null) {
			return ResponseEntity.notFound().build();
		}
		// currentKitchen.setName(kitchen.getName()Long);
		BeanUtils.copyProperties(kitchen, currentKitchen, "id");
		kitchenRepository.save(currentKitchen);

		return ResponseEntity.ok(currentKitchen);
	}
}
