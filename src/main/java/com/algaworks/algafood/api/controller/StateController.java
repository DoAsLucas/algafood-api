package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;
import com.algaworks.algafood.domain.service.StateService;

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
@RequestMapping(value = "/states")
public class StateController {
	
	@Autowired
	StateRepository stateRepository;

	@Autowired
	StateService stateService;

	@GetMapping
	public List<State> listar(){
		return stateRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public State save(@RequestBody State state) {
		return stateService.save(state);
	}

	@PutMapping("/{stateId}")
	public ResponseEntity<State> update(@PathVariable Long stateId, @RequestBody State state) {
		Optional<State> currentState = stateRepository.findById(stateId);

		if (currentState.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(state, currentState.get(), "id");
		State createdState = stateService.save(currentState.get());

		return ResponseEntity.ok(createdState);
	}

	@DeleteMapping("/{stateId}")
	public ResponseEntity<State> delete(@PathVariable Long stateId) {
		try {
			stateService.delete(stateId);
			return ResponseEntity.noContent().build();

		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
