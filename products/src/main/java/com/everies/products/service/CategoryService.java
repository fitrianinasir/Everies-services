package com.everies.products.service;

import com.everies.products.model.CategoryModel;
import com.everies.products.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;



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




    public void truncateTblCategory(){
        categoryRepo.truncateTblCategory();
    }


}
