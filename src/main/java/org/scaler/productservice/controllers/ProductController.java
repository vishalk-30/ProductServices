package org.scaler.productservice.controllers;

import lombok.AllArgsConstructor;
import org.scaler.productservice.dtos.ProductDto;
import org.scaler.productservice.models.Product;
import org.scaler.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    ProductService productService;
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);

    }

    @GetMapping()
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        return productService.replaceProduct(id,productDto);
    }
}
