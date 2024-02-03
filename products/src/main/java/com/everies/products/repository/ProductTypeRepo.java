package com.everies.products.repository;

import com.everies.products.model.ProductTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductTypeRepo extends JpaRepository<ProductTypeModel, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE tbl_sub_category;ALTER SEQUENCE tbl_sub_category_id_seq RESTART WITH 1;", nativeQuery = true)
    void truncateSubCategory();
}
