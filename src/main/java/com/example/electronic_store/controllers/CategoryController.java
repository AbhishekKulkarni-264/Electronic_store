package com.example.electronic_store.controllers;

import com.example.electronic_store.dtos.CategoryDto;
import com.example.electronic_store.dtos.ProductDto;
import com.example.electronic_store.services.CategoryService;
import com.example.electronic_store.services.ProductService;
import com.example.electronic_store.services.impl.CategoryServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryServiceimpl categoryServiceimpl;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryServiceimpl.create(categoryDto), HttpStatus.CREATED);
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("categoryId") String categoryId){
        return new ResponseEntity<>(categoryService.update(categoryDto,categoryId),HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") String categoryId){
        categoryService.delete(categoryId);
        return new ResponseEntity<>("category Deleted Successfully",HttpStatus.NO_CONTENT);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAll(){
        return new ResponseEntity<>(categoryService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getBydId(@PathVariable("categoryId") String categoryId){
        return new ResponseEntity<>(categoryService.getById(categoryId),HttpStatus.OK);
    }

    //create product with category

    @PostMapping("/{categoryId}/products")
    public  ResponseEntity<ProductDto> createProductWIthCategory(@PathVariable("categoryId") String categoryId,@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.createWithCategory(productDto,categoryId),HttpStatus.CREATED);
    }

}
