package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CityRepositoryImpl implements CityRepository{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<City> list() {
		return manager.createQuery("from City", City.class)
				.getResultList();
	}

	@Override
	public City get(Long id) {
		return manager.find(City.class, id);
	}

	@Transactional
	@Override
	public City save(City City) {
		return manager.merge(City);
	}

	@Transactional
	@Override
	public void remove(Long cityId) {
		City city = get(cityId);

		if (city == null) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(city);
	}
}
