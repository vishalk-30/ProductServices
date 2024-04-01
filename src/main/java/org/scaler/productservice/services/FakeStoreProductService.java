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
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStore.getAllProducts();
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto dto : fakeStoreProductDtos){
            products.add(convertFakeStoreDtoToProduct(dto));
        } return products;

    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        Product product = convertProductFromProductDto(productDto);
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreDto(product);
        FakeStoreProductDto responseFakeStoreDto = fakeStore.replaceProduct(id, fakeStoreProductDto);
        return convertFakeStoreDtoToProduct(responseFakeStoreDto);
    }

    private Product convertProductFromProductDto(ProductDto productDto) {
        Product product = Product.builder()
                .id(productDto.getId())
                .title(productDto.getTitle())
                .image(productDto.getImage())
                .price(productDto.getPrice())
                .category(Category.builder()
                        .desc(productDto.getCategory())
                        .build())
                .description(productDto.getDescription())
                .build();

        return product;
    }

    private FakeStoreProductDto convertProductToFakeStoreDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = FakeStoreProductDto.builder()
                .title(product.getTitle())
                .category(product.getCategory().getDesc())
                .description(product.getDescription())
                .id(product.getId())
                .image(product.getImage())
                .price(product.getPrice())
                .build();

       return fakeStoreProductDto;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = Product.builder()
                .description(fakeStoreProductDto.getDescription())
                .category(Category.builder()
                        .desc(fakeStoreProductDto.getCategory())
                        .build())
                .price(fakeStoreProductDto.getPrice())
                .image(fakeStoreProductDto.getImage())
                .title(fakeStoreProductDto.getTitle())
                .id(fakeStoreProductDto.getId())
                .build();

        return product;
    }
}
