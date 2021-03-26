package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Kitchen;

public interface KitchenRepository {
	List<Kitchen> list();
	Kitchen get(Long id);
	Kitchen save(Kitchen kitchen);
	void remove(Kitchen kitchen);
}
