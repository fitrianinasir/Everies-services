package com.everies.products.repository;

import com.everies.products.model.CategoryTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryTypeRepo extends JpaRepository<CategoryTypeModel, Integer> {
}
