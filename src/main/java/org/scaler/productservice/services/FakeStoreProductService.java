package org.scaler.productservice.services;

import lombok.AllArgsConstructor;
import org.scaler.productservice.dtos.FakeStoreProductDto;
import org.scaler.productservice.dtos.ProductDto;
import org.scaler.productservice.models.Category;
import org.scaler.productservice.models.Product;
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

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        Product product = convertProductFromProductDto(productDto);
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreDto(product);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto responseFakeStoreDto =  restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreDtoToProduct(responseFakeStoreDto);
    }

    private Product convertProductFromProductDto(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setDesc(productDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto convertProductToFakeStoreDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getDesc());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImage());
       return fakeStoreProductDto;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setDesc(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
