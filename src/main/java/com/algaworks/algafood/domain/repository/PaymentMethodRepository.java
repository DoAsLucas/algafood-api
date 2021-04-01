package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.PaymentMethod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
