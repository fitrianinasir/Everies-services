package com.everies.products.controller;


import com.everies.products.dto.ResMsg;
import com.everies.products.dto.UploadFileRes;
import com.everies.products.model.CategoryModel;
import com.everies.products.model.SubCategoryModel;
import com.everies.products.service.CategoryService;
import com.everies.products.service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    CategoryService categoryService;



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



    // =========================== SUB CATEGORY ===================================

    @GetMapping("/sub-categories")
    public @ResponseBody ResponseEntity<ResMsg> getAllSubCategories(){
        List<SubCategoryModel> sub_categories = categoryService.getAllSubCategories();
        ResMsg response = new ResMsg(200, "Sub categories retrieved successfully", sub_categories);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/sub-category/{id}")
    public @ResponseBody ResponseEntity<ResMsg> getSubCategory(@PathVariable("id") Integer id){
        Optional<SubCategoryModel> sub_categories = categoryService.getSubCategory(id);
        ResMsg response = new ResMsg(200, "sub category retrieved successfully", sub_categories);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/sub-category")
    public @ResponseBody ResponseEntity<ResMsg> createSubCategory(@RequestBody SubCategoryModel subCategoriesModel){
        SubCategoryModel sub_categories = categoryService.createSubCategory(subCategoriesModel);
        ResMsg response = new ResMsg(201, "sub category created successfully", sub_categories);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/sub-category/{id}")
    public @ResponseBody ResponseEntity<ResMsg> updateSubCategory(@PathVariable("id") Integer id, @RequestBody SubCategoryModel subCategoriesModel){
        SubCategoryModel sub_categories = categoryService.updateSubCategory(id, subCategoriesModel);
        ResMsg response = new ResMsg(200, "sub category updated successfully", sub_categories);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/sub-category/{id}")
    public @ResponseBody ResponseEntity<ResMsg> deleteSubCategory(@PathVariable("id") Integer id){
        categoryService.deleteSubCategory(id);
        ResMsg response = new ResMsg(200, "sub category deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //================= TRUNCATE ========================

    @DeleteMapping("/truncate-category")
    public void truncateTblCategory(){
        categoryService.truncateTblCategory();
    }

    @DeleteMapping("/truncate-subcategory")
    public void truncateTblSubCategory(){
        categoryService.truncateTblSubCategory();
    }

}
