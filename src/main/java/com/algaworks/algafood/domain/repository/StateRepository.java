package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.State;

public interface StateRepository {
	List<State> listar();
	State buscar(Long id);
	State salvar(State state);
	void remover(State state);
}
