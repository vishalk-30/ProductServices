package org.scaler.productservice.controllers;

import lombok.AllArgsConstructor;
import org.scaler.productservice.dtos.ProductDto;
import org.scaler.productservice.exception.ProductNotFoundException;
import org.scaler.productservice.models.Product;
import org.scaler.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    ProductService productService;
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product =  productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        Product product = productService.replaceProduct(id, productDto);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    
}
