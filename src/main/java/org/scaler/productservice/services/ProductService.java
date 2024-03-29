package org.scaler.productservice.services;

import org.scaler.productservice.dtos.ProductDto;
import org.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();

    Product replaceProduct(Long id, ProductDto productDto);
}
