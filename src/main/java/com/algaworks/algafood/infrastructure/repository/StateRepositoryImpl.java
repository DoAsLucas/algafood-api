package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StateRepositoryImpl implements StateRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<State> listar() {
		return manager.createQuery("from State", State.class)
				.getResultList();
	}
	
	@Override
	public State buscar(Long id) {
		return manager.find(State.class, id);
	}
	
	@Transactional
	@Override
	public State salvar(State state) {
		return manager.merge(state);
	}
	
	@Transactional
	@Override
	public void remover(State state) {
		state = buscar(state.getId());
		manager.remove(state);
	}
}
