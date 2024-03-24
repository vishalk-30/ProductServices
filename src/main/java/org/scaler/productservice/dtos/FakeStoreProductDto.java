package org.scaler.productservice.dtos;


import lombok.Getter;
import lombok.Setter;
import org.scaler.productservice.models.Product;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String desc;
    private Double price;
    private String image;
    private String category;


}
