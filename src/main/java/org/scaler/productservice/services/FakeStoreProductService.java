package org.scaler.productservice.services;

import lombok.AllArgsConstructor;
import org.scaler.productservice.dtos.FakeStoreProductDto;
import org.scaler.productservice.dtos.ProductDto;
import org.scaler.productservice.models.Category;
import org.scaler.productservice.models.Product;
import org.scaler.productservice.thirdParty.FakeStore;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class FakeStoreProductService implements ProductService{
    private FakeStore fakeStore;
    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto = fakeStore.getProductById(id);
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

//        product.setId(productDto.getId());
//        product.setDescription(productDto.getDescription());
//        product.setImage(productDto.getImage());
//        product.setTitle(productDto.getTitle());
//        product.setPrice(productDto.getPrice());
//        Category category = new Category();
//        category.setDesc(productDto.getCategory());
//        product.setCategory(category);
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
//        fakeStoreProductDto.setDescription(product.getDescription());
//        fakeStoreProductDto.setCategory(product.getCategory().getDesc());
//        fakeStoreProductDto.setTitle(product.getTitle());
//        fakeStoreProductDto.setPrice(product.getPrice());
//        fakeStoreProductDto.setImage(product.getImage());
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
//        product.setId(fakeStoreProductDto.getId());
//        product.setDescription(fakeStoreProductDto.getDescription());
//        product.setImage(fakeStoreProductDto.getImage());
//        product.setTitle(fakeStoreProductDto.getTitle());
//        product.setPrice(fakeStoreProductDto.getPrice());
//        Category category = new Category();
//        category.setDesc(fakeStoreProductDto.getCategory());
//        product.setCategory(category);

        return product;
    }
}
