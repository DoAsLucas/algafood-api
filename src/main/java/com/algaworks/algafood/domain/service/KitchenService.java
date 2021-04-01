package com.algaworks.algafood.domain.service;


import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {

	@Autowired
	KitchenRepository kitchenRepository;

	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}

	public void delete(Long kitchenId) {
		try {
			kitchenRepository.deleteById(kitchenId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("There is no kitchen register with ID %d", kitchenId));

		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Kitchen which ID is %d cannot be removed, because it is in use", kitchenId));
		}
	}
}
