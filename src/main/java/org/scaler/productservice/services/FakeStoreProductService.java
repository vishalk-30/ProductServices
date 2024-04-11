package org.scaler.productservice.services;

import lombok.AllArgsConstructor;
import org.scaler.productservice.dtos.FakeStoreProductDto;
import org.scaler.productservice.dtos.ProductDto;
import org.scaler.productservice.exception.ProductNotFoundException;
import org.scaler.productservice.models.Category;
import org.scaler.productservice.models.Product;
import org.scaler.productservice.thirdParty.FakeStore;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
@AllArgsConstructor
public class FakeStoreProductService implements ProductService{
    private FakeStore fakeStore;
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = fakeStore.getProductById(id);
        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product not found for id : " + id);
        }
        return convertProductFromFakeStoreDto(fakeStoreProductDto);
    }

    

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStore.getAllProducts();
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto dto : fakeStoreProductDtos){
            products.add(convertProductFromFakeStoreDto(dto));
        } return products;

    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        Product product = convertProductFromProductDto(productDto);
        FakeStoreProductDto fakeStoreProductDto = convertFakeStoreDtoFromProduct(product);
        FakeStoreProductDto responseFakeStoreDto = fakeStore.replaceProduct(id, fakeStoreProductDto);
        return convertProductFromFakeStoreDto(responseFakeStoreDto);
    }

    @Override
    public Product addProduct(ProductDto productDto) {
        Product product = convertProductFromProductDto(productDto);
        FakeStoreProductDto fakeStoreProductDto = convertFakeStoreDtoFromProduct(product);
        FakeStoreProductDto responseFakesStoreDto = fakeStore.addProduct(fakeStoreProductDto);
        return convertProductFromFakeStoreDto(responseFakesStoreDto);
    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotFoundException {

        FakeStoreProductDto fakeStoreProductDto = fakeStore.deleteProduct(id);
        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product not found for id: " + id);
        }
        return convertProductFromFakeStoreDto(fakeStoreProductDto) ;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStore.getProductsByCategory(category);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
            products.add(convertProductFromFakeStoreDto(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public List<String> getAllCategory() {
        String[] categories = fakeStore.getAllCategory();
        List<String> categoryList = new ArrayList<>();
        categoryList.addAll(Arrays.asList(categories));
        return categoryList;
    }

    private Product convertProductFromProductDto(ProductDto productDto) {
        Category category = new Category();
        category.setTitle(productDto.getTitle());
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        product.setId(product.getId());
        product.setImage(productDto.getImage());

        return product;
    }


    private FakeStoreProductDto convertFakeStoreDtoFromProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto  = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImage());
        fakeStoreProductDto.setCategory(product.getCategory().getTitle());

       return fakeStoreProductDto;
    }

    private Product convertProductFromFakeStoreDto(FakeStoreProductDto fakeStoreProductDto) {
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getTitle());

        Product product = new Product();
        product.setCategory(category);
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());



        return product;
    }
}
