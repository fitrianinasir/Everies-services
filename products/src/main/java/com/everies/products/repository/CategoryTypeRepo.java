package com.everies.products.repository;

import com.everies.products.model.TypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryTypeRepo extends JpaRepository<TypeModel, Integer> {
}
