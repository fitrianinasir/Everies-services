package com.everies.products.repository;

import com.everies.products.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductModel, Integer> {
}
