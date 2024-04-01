package org.scaler.productservice.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDto {
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
}
