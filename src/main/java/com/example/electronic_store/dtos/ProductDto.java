package com.example.electronic_store.dtos;

import com.example.electronic_store.entities.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String productId;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private int price;
    @NotBlank
    private int quantity;
    private CategoryDto category;
}
