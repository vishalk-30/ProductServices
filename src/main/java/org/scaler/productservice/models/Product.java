package org.scaler.productservice.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private Category category;
}
