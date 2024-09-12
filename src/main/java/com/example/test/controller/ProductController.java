package com.example.test.controller;

import com.example.test.model.Product;
import com.example.test.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepo productRepo;


    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        try {
            List<Product>productList=new ArrayList<>();
            productRepo.findAll().forEach(productList::add);
            if(productList.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(productList,HttpStatus.OK);

        } catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductsById(@PathVariable Long id){
        Optional<Product> productData=productRepo.findById(id);

        if(productData.isPresent())
        {
            return new ResponseEntity<>(productData.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
        Product productObj = productRepo.save(product);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @PutMapping("/updateProductById/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long id,@RequestBody Product newProductData){
         Optional<Product> oldproductData=productRepo.findById(id);

         if(oldproductData.isPresent()){
             Product updatedProductData= oldproductData.get();
             updatedProductData.setTitle(newProductData.getTitle());
             updatedProductData.setPrice(newProductData.getPrice());
             updatedProductData.setDescription(newProductData.getDescription());
             updatedProductData.setImageUrl(newProductData.getImageUrl());

             Product productObj = productRepo.save(updatedProductData);

             return new ResponseEntity<>(productObj,HttpStatus.OK);


         }
         return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable Long id){
        productRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);


    }
}
