package org.scaler.productservice.thirdParty;

import lombok.AllArgsConstructor;
import org.scaler.productservice.dtos.FakeStoreProductDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;


@Component
@AllArgsConstructor
public class FakeStore {
    private RestTemplate restTemplate;
    private final String fakeStoreUrl = "https://fakestoreapi.com/products/";

    public FakeStoreProductDto getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject(fakeStoreUrl + id, FakeStoreProductDto.class);
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto[] getAllProducts() {

        FakeStoreProductDto[] fakeStoreProductDtos =
                restTemplate.getForObject(fakeStoreUrl,
                        FakeStoreProductDto[].class);

        return fakeStoreProductDtos;
    }

    public FakeStoreProductDto replaceProduct(Long id, FakeStoreProductDto fakeStoreProductDto) {


        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto,
                FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new
                HttpMessageConverterExtractor(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());
        FakeStoreProductDto responseFakeStoreDto =  restTemplate.execute(fakeStoreUrl + id,
                HttpMethod.PUT, requestCallback, responseExtractor);


        return responseFakeStoreDto;
    }

    public FakeStoreProductDto addProduct(FakeStoreProductDto fakeStoreProductDto) {
        return restTemplate.postForObject(fakeStoreUrl,fakeStoreProductDto,FakeStoreProductDto.class);

    }

    public FakeStoreProductDto deleteProduct(Long id) {
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        FakeStoreProductDto responseFakeStoreDto = restTemplate.execute(fakeStoreUrl + id, HttpMethod.DELETE, requestCallback, responseExtractor).getBody();
        return responseFakeStoreDto;
    }



    }

