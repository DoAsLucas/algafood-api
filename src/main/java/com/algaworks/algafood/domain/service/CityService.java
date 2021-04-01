package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.repository.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityService {
	
	@Autowired
	CityRepository cityRepository;

	@Autowired
	StateRepository stateRepository;

	public City save(City city) {
		Long stateId = city.getState().getId();
		stateRepository.findById(stateId)
			.orElseThrow(() -> new EntityNotFoundException(
				String.format("State ID %d not found.", stateId)));

		City createdCity = cityRepository.save(city);
		return createdCity;
	}

	public void delete(Long cityId) {
		try {
			cityRepository.deleteById(cityId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("There is no city register with ID %d", cityId));

		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("City which ID is %d cannot be removed, because it is in use", cityId));
		}
	}
}
