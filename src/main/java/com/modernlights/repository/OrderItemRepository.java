package com.modernlights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modernlights.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
