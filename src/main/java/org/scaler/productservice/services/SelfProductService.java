package org.scaler.productservice.services;

import lombok.AllArgsConstructor;
import org.scaler.productservice.dtos.CategoryDto;
import org.scaler.productservice.dtos.ProductDto;
import org.scaler.productservice.exception.CategoryNotFoundException;
import org.scaler.productservice.exception.ProductNotFoundException;
import org.scaler.productservice.models.Category;
import org.scaler.productservice.models.Product;
import org.scaler.productservice.repository.CategoryRepository;
import org.scaler.productservice.repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Primary
@AllArgsConstructor
public class SelfProductService implements ProductService {

    ProductRepository productRepository;
    CategoryService categoryService;


    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product Not found for this id:" + id);
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        return null;
    }

    @Override
    public Product addProduct(ProductDto productDto) throws CategoryNotFoundException {
        Product product = convertProductFromProductDto(productDto);
        CategoryDto categoryDto = productDto.getCategory();
        Category category = new Category();

        if (categoryDto.getId() == null) {
            category.setTitle(categoryDto.getTitle());
        } else{
            category = categoryService.getCategoryByIdInternal(categoryDto.getId());
            if (category == null) {
                throw new CategoryNotFoundException("Category Not Found for this id:" + categoryDto.getId());
            }

        }
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public List<String> getAllCategory() {
        return List.of();
    }

    private Product convertProductFromProductDto(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        return product;
    }


}
