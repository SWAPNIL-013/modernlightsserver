package com.modernlights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modernlights.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	 Cart findByUserId(Long userId);
}
