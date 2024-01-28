package com.everies.order.repository;

import com.everies.order.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderModel, Integer> {
}
