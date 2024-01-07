package com.everies.products.service;

import com.everies.products.model.ProductModel;
import com.everies.products.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;


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
}
