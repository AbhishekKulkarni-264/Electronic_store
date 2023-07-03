package com.example.electronic_store.services;

import com.example.electronic_store.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto create(ProductDto productDto);
    ProductDto update(ProductDto productDto,String productId);
    void delete(String productId);

    List<ProductDto> getAll();
    ProductDto getById(String Id);

    //Create product with category
    ProductDto createWithCategory(ProductDto productDto,String categoryId);
}
