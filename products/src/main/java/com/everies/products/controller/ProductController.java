package com.everies.products.controller;

import com.everies.products.dto.ResMsg;
import com.everies.products.model.ProductModel;
import com.everies.products.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    ProductService productService;


    @GetMapping("products")
    public ResponseEntity<ResMsg> getAllProducts(){
        List<ProductModel> products = productService.getAllProducts();
        ResMsg response = new ResMsg(200, "Products retrieved successfully", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ResMsg> getProduct(@PathVariable("id") Integer id){
        Optional<ProductModel> product = productService.getProduct(id);
        ResMsg response = new ResMsg(200, "Product retrieved successfully", product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<ResMsg> createProduct(@RequestBody ProductModel productModel){
        ProductModel product = productService.createProduct(productModel);
        ResMsg response = new ResMsg(200, "Product created successfully", product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ResMsg> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductModel productModel){
        ProductModel product = productService.updateProduct(id, productModel);
        ResMsg response = new ResMsg(200, "Product updated successfully", product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public @ResponseBody ResponseEntity<ResMsg> deleteProduct(@PathVariable("id") Integer id){
        productService.deleteProduct(id);
        ResMsg response = new ResMsg(200, "Product deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/truncate-product")
    public void truncateTblProduct(){
        productService.truncateTblProduct();
    }
}
