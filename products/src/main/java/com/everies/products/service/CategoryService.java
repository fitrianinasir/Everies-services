package com.everies.products.service;

import com.everies.products.model.CategoryModel;
import com.everies.products.model.SubCategoryModel;
import com.everies.products.repository.CategoryRepo;
import com.everies.products.repository.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    SubCategoryRepo subCategoryRepo;

    public List<CategoryModel> getAllCategories(){
        return categoryRepo.findAll();
    }

    public Optional<CategoryModel> getCategory(Integer id){
        return categoryRepo.findById(id);
    }

    public CategoryModel createCategory(CategoryModel categoryModel){

        return categoryRepo.save(categoryModel);
    }

    public CategoryModel updateCategory(Integer id, CategoryModel categoryModel){
        categoryModel.setId(id);
        return categoryRepo.save(categoryModel);
    }

    public void deleteCategory(Integer id){
        categoryRepo.deleteById(id);
    }

    // =========================== SUB CATEGORY ===================================
    public List<SubCategoryModel> getAllSubCategories(){
        return subCategoryRepo.findAll();
    }

    public Optional<SubCategoryModel> getSubCategory(Integer id){
        return subCategoryRepo.findById(id);
    }

    public SubCategoryModel createSubCategory(SubCategoryModel SubCategoryModel){
        return subCategoryRepo.save(SubCategoryModel);
    }

    public SubCategoryModel updateSubCategory(Integer id, SubCategoryModel subCategoryModel){
        subCategoryModel.setId(id);
        return subCategoryRepo.save(subCategoryModel);
    }

    public void deleteSubCategory(Integer id){
        subCategoryRepo.deleteById(id);
    }

    //==================== TRUNCATE DATA ============================


    public void truncateTblCategory(){
        categoryRepo.truncateTblCategory();
    }

    public void truncateTblSubCategory(){
        subCategoryRepo.truncateSubCategory();
    }
}
