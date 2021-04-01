package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Kitchen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
}
