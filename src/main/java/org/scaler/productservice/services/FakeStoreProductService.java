package org.scaler.productservice.services;

import lombok.AllArgsConstructor;
import org.scaler.productservice.dtos.FakeStoreProductDto;
import org.scaler.productservice.models.Category;
import org.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class);
        if(fakeStoreProductDto == null){
            return null;
        }
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] fakeStoreProductDtos =
                restTemplate.getForObject("https://fakestoreapi.com/products",
                        FakeStoreProductDto[].class);
        if (fakeStoreProductDtos == null){
            return null;
        }
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
            products.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
        }


        return products;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setDesc(fakeStoreProductDto.getDesc());
        product.setImage(fakeStoreProductDto.getImage());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setDesc(fakeStoreProductDto.getDesc());
        product.setCategory(category);
        return product;
    }
}
