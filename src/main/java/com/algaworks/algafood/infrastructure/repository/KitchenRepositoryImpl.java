package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

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
	public void remove(Kitchen kitchen) {
		kitchen = get(kitchen.getId());
		manager.remove(kitchen);
	}
}
