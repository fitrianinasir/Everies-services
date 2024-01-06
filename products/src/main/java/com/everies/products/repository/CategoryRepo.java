package com.everies.products.repository;

import com.everies.products.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE tbl_main_category;ALTER SEQUENCE tbl_main_category_id_seq1 RESTART WITH 1;", nativeQuery = true)
    void truncateTblCategory();

}
