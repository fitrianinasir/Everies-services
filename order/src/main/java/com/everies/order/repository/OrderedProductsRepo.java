package com.everies.order.repository;

import com.everies.order.model.OrderedProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductsRepo extends JpaRepository<OrderedProductsModel, Integer> {
}
