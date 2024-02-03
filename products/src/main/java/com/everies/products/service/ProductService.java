package com.everies.products.service;

import com.everies.products.model.ProductModel;
import com.everies.products.model.ProductTypeModel;
import com.everies.products.repository.ProductRepo;
import com.everies.products.repository.ProductTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProductTypeRepo productTypeRepo;
    public List<ProductModel> getAllProducts(){
        return productRepo.findAll();
    }

    public Optional<ProductModel> getProduct(Integer id){
        return productRepo.findById(id);
    }

    public ProductModel createProduct(ProductModel productModel){
        return productRepo.save(productModel);
    }

    public ProductModel updateProduct(Integer id, ProductModel productModel){
        productModel.setId(id);
        return productRepo.save(productModel);
    }

    public void deleteProduct(Integer id){
        productRepo.deleteById(id);
    }

    public void truncateTblProduct(){
        productRepo.truncateTblProduct();
    }


//    PRODUCT TYPES

    // =========================== PRODUCT TYPES ===================================
    public List<ProductTypeModel> getAllProductTypes(){
        return productTypeRepo.findAll();
    }

    public Optional<ProductTypeModel> getProductTypeById(Integer id){
        return productTypeRepo.findById(id);
    }

    public ProductTypeModel createProductType(ProductTypeModel ProductTypeModel){
        return productTypeRepo.save(ProductTypeModel);
    }

    public ProductTypeModel updateProductType(Integer id, ProductTypeModel productTypeModel){
        productTypeModel.setId(id);
        return productTypeRepo.save(productTypeModel);
    }

    public void deleteProductType(Integer id){
        productTypeRepo.deleteById(id);
    }

    //==================== TRUNCATE DATA ============================
    public void truncateTblProductType(){
        productTypeRepo.truncateSubCategory();
    }
}
