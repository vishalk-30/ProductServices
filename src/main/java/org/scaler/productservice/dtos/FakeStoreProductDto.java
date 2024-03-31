package org.scaler.productservice.dtos;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.scaler.productservice.models.Product;

@Getter
@Setter
@Builder
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;


}
