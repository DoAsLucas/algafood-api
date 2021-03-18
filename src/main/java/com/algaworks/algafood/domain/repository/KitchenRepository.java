package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Kitchen;

public interface KitchenRepository {
	
	List<Kitchen> listar();
	Kitchen buscar(Long id);
	Kitchen salvar(Kitchen kitchen);
	void remover(Kitchen kitchen);
}
