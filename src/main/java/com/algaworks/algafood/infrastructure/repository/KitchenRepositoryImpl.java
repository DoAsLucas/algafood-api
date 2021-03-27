package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class KitchenRepositoryImpl implements KitchenRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Kitchen> list() {
		return manager.createQuery("from Kitchen", Kitchen.class)
				.getResultList();
	}
	
	@Override
	public Kitchen get(Long id) {
		return manager.find(Kitchen.class, id);
	}
	
	@Transactional
	@Override
	public Kitchen save(Kitchen kitchen) {
		return manager.merge(kitchen);
	}
	
	@Transactional
	@Override
	public void delete(Long kitchenId) {
		Kitchen kitchen = get(kitchenId);

		if (kitchen == null) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(kitchen);
	}
}
