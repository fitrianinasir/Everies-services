package com.everies.products.repository;

import com.everies.products.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepo extends JpaRepository<ProductModel, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE tbl_product;ALTER SEQUENCE tbl_product_id_seq RESTART WITH 1;", nativeQuery = true)
    void truncateTblProduct();
}
