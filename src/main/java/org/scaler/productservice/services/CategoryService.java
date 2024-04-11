package org.scaler.productservice.services;

import lombok.AllArgsConstructor;
import org.scaler.productservice.dtos.CategoryDto;
import org.scaler.productservice.models.Category;
import org.scaler.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public Category saveCategoryInternal(CategoryDto categoryDto) {
        Category category = new Category();
        category.setTitle(categoryDto.getTitle());
        return categoryRepository.save(category);
    }

    public Category getCategoryByIdInternal(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

}
