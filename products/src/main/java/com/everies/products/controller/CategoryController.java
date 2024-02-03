package com.everies.products.controller;


import com.everies.products.dto.ResMsg;
import com.everies.products.model.CategoryModel;
import com.everies.products.model.CategoryTypeModel;
import com.everies.products.repository.CategoryTypeRepo;
import com.everies.products.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryTypeRepo categoryTypeRepo;



//    GET ALL
    @GetMapping("/categories")
    public @ResponseBody ResponseEntity<ResMsg> getAllCategories(){
        List<CategoryModel> categories = categoryService.getAllCategories();
        ResMsg response = new ResMsg(200, "Categories retrieved successfully", categories);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public @ResponseBody ResponseEntity<ResMsg> getCategory(@PathVariable("id") Integer id){
        Optional<CategoryModel> category = categoryService.getCategory(id);
        ResMsg response = new ResMsg(200, "Category retrieved successfully", category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/category")
    public @ResponseBody ResponseEntity<ResMsg> createCategory(@RequestBody CategoryModel categoryModel){
        CategoryModel category = categoryService.createCategory(categoryModel);
        ResMsg response = new ResMsg(201, "Category created successfully", category);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/category/{id}")
    public @ResponseBody ResponseEntity<ResMsg> updateCategory(@PathVariable("id") Integer id, @RequestBody CategoryModel categoryModel){
        CategoryModel category = categoryService.updateCategory(id, categoryModel);
        ResMsg response = new ResMsg(200, "Category updated successfully", category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public @ResponseBody ResponseEntity<ResMsg> deleteCategory(@PathVariable("id") Integer id){
        categoryService.deleteCategory(id);
        ResMsg response = new ResMsg(200, "Category deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //================= TYPE ========================
    @GetMapping("/category-types")
    public @ResponseBody ResponseEntity<ResMsg> getCategoryTypes(){
        List<CategoryTypeModel> types = categoryTypeRepo.findAll();
        ResMsg response = new ResMsg(200, "Types retrieved successfully", types);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/category-type/{id}")
    public @ResponseBody ResponseEntity<ResMsg> getCategoryType(@PathVariable("id") Integer id){
        Optional<CategoryTypeModel> type = categoryTypeRepo.findById(id);
        ResMsg response = new ResMsg(200, "type retrieved successfully", type);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PostMapping("/category-type")
    public @ResponseBody ResponseEntity<ResMsg> createCategoryType(@RequestBody CategoryTypeModel categoryTypeModel){
        CategoryTypeModel type = categoryTypeRepo.save(categoryTypeModel);
        ResMsg response = new ResMsg(201, "type created successfully", type);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/category-type/{id}")
    public @ResponseBody ResponseEntity<ResMsg> updateCategoryType(@PathVariable("id") Integer id, @RequestBody CategoryTypeModel categoryTypeModel){
        categoryTypeModel.setId(id);
        CategoryTypeModel type = categoryTypeRepo.save(categoryTypeModel);
        ResMsg response = new ResMsg(200, "type updated successfully", type);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/category-type/{id}")
    public @ResponseBody ResponseEntity<ResMsg> deleteCategoryType(@PathVariable("id") Integer id){
        categoryTypeRepo.deleteById(id);
        ResMsg response = new ResMsg(200, "type deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //================= TRUNCATE ========================

    @DeleteMapping("/truncate-category")
    public void truncateTblCategory(){
        categoryService.truncateTblCategory();
    }



}
