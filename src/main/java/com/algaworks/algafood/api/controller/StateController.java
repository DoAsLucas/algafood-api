package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/states")
public class StateController {
	
	@Autowired
	StateRepository stateRepository;

	@GetMapping
	public List<State> listar(){
		return stateRepository.listar();
	}
}
