package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StateService {
	
	@Autowired
	StateRepository stateRepository;

	public State save(State state) {
		return stateRepository.save(state);
	}

	public void delete(Long stateId) {
		try {
			stateRepository.deleteById(stateId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("There is no state register with ID %d", stateId));

		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("State which ID is %d cannot be removed, because it is in use", stateId));
		}
	}
}
