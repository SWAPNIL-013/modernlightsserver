package com.modernlights.repository;

import com.modernlights.model.Cart;
import com.modernlights.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import com.modernlights.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);


}
