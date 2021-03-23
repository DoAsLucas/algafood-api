package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Permission;

public interface PermissionRepository {
	List<Permission> listar();
	Permission buscar(Long id);
	Permission salvar(Permission permission);
	void remover(Permission permission);
}
