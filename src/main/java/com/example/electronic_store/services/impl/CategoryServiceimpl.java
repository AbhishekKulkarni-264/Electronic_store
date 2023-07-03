package com.example.electronic_store.services.impl;

import com.example.electronic_store.dtos.CategoryDto;
import com.example.electronic_store.entities.Category;
import com.example.electronic_store.exceptions.ResourceNotFoundException;
import com.example.electronic_store.repositories.CategoryRepository;
import com.example.electronic_store.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceimpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        String categoryId= UUID.randomUUID().toString();
        categoryDto.setCategotyId(categoryId);
        Category category= mapper.map(categoryDto, Category.class);
        Category saved=categoryRepository.save(category);
        return mapper.map(saved,CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
       Category category= categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category not found"));
       category.setTitle(categoryDto.getTitle());
       category.setDescription(categoryDto.getDescription());
       category.setCoverImage(categoryDto.getCoverImage());
      Category updated= categoryRepository.save(category);
       return mapper.map(updated,CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {
        Category category= categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category not found"));
         categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories=categoryRepository.findAll();
        List<CategoryDto> cat=categories.stream().map(category -> mapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return cat;
    }

    @Override
    public CategoryDto getById(String categoryId) {
        Category category= categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category not found"));
        return mapper.map(category,CategoryDto.class);
    }
}
