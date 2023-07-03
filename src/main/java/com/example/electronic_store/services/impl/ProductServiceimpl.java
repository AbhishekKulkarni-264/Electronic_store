package com.example.electronic_store.services.impl;

import com.example.electronic_store.dtos.ProductDto;
import com.example.electronic_store.entities.Category;
import com.example.electronic_store.entities.Product;
import com.example.electronic_store.exceptions.ResourceNotFoundException;
import com.example.electronic_store.repositories.CategoryRepository;
import com.example.electronic_store.repositories.ProductRepository;
import com.example.electronic_store.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceimpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public ProductDto create(ProductDto productDto) {
        String productId= UUID.randomUUID().toString();
        productDto.setProductId(productId);
        Product product=mapper.map(productDto,Product.class);
        Product saved=productRepository.save(product);
        return mapper.map(saved,ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        Product product=productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found"));
        product.setDescription(productDto.getDescription());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        Product updated=productRepository.save(product);
        return mapper.map(product,ProductDto.class);
    }

    @Override
    public void delete(String productId) {
        Product product=productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products=productRepository.findAll();
        List<ProductDto> prod=products.stream().map(product -> mapper.map(product,ProductDto.class)).collect(Collectors.toList());
        return prod;
    }

    @Override
    public ProductDto getById(String productId) {
        Product product=productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found"));
        return mapper.map(product,ProductDto.class);
    }

    @Override
    public ProductDto createWithCategory(ProductDto productDto, String categoryId) {
        Category category= categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category not found"));
        String productId= UUID.randomUUID().toString();
        productDto.setProductId(productId);
        Product product=mapper.map(productDto,Product.class);
        product.setCategory(category);
        Product saved=productRepository.save(product);
        return mapper.map(saved,ProductDto.class);
    }
}
