package com.algaworks.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.algaworks.algafood.domain.model.Kitchen;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class CadastroKitchen {
	
	@PersistenceContext
	private EntityManager manager;

	public List<Kitchen> listar(){
		return manager.createQuery("from Kitchen", Kitchen.class)
				.getResultList();
	}

	@Transactional
	public Kitchen adicionar(Kitchen kitchen) {
		return manager.merge(kitchen);
	}
}
