package org.scaler.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String title;
}
