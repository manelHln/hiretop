package com.backend.hiretop.service;

import com.backend.hiretop.domain.Category;
import com.backend.hiretop.domain.Job;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.dto.ResponseVOBuilder;
import com.backend.hiretop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(String category, Job job) {
        if (categoryRepository.existsByName(category)) {
            return categoryRepository.findByName(category);
        }
        Category cat = Category.builder().name(category).build();
        cat.getJobs().add(job);
        return categoryRepository.save(cat);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public ResponseVO<List<Category>> getAllCategories() {
        return new ResponseVOBuilder<List<Category>>().success().addData(categoryRepository.findAll()).build();
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDetails.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}