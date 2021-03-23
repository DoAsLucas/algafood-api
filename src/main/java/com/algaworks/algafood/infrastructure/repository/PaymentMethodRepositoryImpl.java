package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algaworks.algafood.domain.model.PaymentMethod;
import com.algaworks.algafood.domain.repository.PaymentMethodRepository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<PaymentMethod> listar() {
		return manager.createQuery("from PaymentMethod", PaymentMethod.class)
				.getResultList();
	}
	
	@Override
	public PaymentMethod buscar(Long id) {
		return manager.find(PaymentMethod.class, id);
	}
	
	@Transactional
	@Override
	public PaymentMethod salvar(PaymentMethod paymentMethod) {
		return manager.merge(paymentMethod);
	}
	
	@Transactional
	@Override
	public void remover(PaymentMethod paymentMethod) {
		paymentMethod = buscar(paymentMethod.getId());
		manager.remove(paymentMethod);
	}
	
}
