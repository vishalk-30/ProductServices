package org.scaler.productservice.services;

import org.scaler.productservice.dtos.ProductDto;
import org.scaler.productservice.exception.ProductNotFoundException;
import org.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product replaceProduct(Long id, ProductDto productDto);

    Product addProduct(ProductDto productDto);

    Product deleteProduct(Long id) throws ProductNotFoundException;
}
