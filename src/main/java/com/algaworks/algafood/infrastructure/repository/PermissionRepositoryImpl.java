package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algaworks.algafood.domain.model.Permission;
import com.algaworks.algafood.domain.repository.PermissionRepository;

import org.springframework.transaction.annotation.Transactional;

public class PermissionRepositoryImpl implements PermissionRepository{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permission> listar() {
		return manager.createQuery("from Permission", Permission.class)
				.getResultList();
	}
	
	@Override
	public Permission buscar(Long id) {
		return manager.find(Permission.class, id);
	}
	
	@Transactional
	@Override
	public Permission salvar(Permission permission) {
		return manager.merge(permission);
	}
	
	@Transactional
	@Override
	public void remover(Permission permission) {
		permission = buscar(permission.getId());
		manager.remove(permission);
	}
}
