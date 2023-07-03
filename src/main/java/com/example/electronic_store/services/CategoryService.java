package com.example.electronic_store.services;

import com.example.electronic_store.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
   CategoryDto create(CategoryDto categoryDto);
   CategoryDto update(CategoryDto categoryDto,String categoryId);
   void delete(String categoryId);
   List<CategoryDto> getAll();
   CategoryDto getById(String categoryId);
}
