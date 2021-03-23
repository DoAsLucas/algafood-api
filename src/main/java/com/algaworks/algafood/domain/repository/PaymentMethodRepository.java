package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.PaymentMethod;

public interface PaymentMethodRepository {
	List<PaymentMethod> listar();
	PaymentMethod buscar(Long id);
	PaymentMethod salvar(PaymentMethod paymentMethod);
	void remover(PaymentMethod paymentMethod);
}
