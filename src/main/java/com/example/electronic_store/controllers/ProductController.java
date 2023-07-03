package com.example.electronic_store.controllers;

import com.example.electronic_store.dtos.ProductDto;
import com.example.electronic_store.services.impl.ProductServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceimpl productServiceimpl;

    @PostMapping("/")
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productServiceimpl.create(productDto), HttpStatus.CREATED);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto, @PathVariable("productId") String productId){
        return  new ResponseEntity<>(productServiceimpl.update(productDto,productId),HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> delete(@PathVariable("productId") String productId){
        productServiceimpl.delete(productId);
        return new ResponseEntity<>("Product deleted Successfully",HttpStatus.NO_CONTENT);
    }
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAll(){
        return new ResponseEntity<>(productServiceimpl.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getById(@PathVariable("productId") String productId){
        return  new ResponseEntity<>(productServiceimpl.getById(productId),HttpStatus.OK);
    }
}
