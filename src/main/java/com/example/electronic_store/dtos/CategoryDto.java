package com.example.electronic_store.dtos;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {


    private  String categotyId;
   @NotBlank
   @Min(value = 4,message = "title must be of min 4 characters")
    private String title;
   @NotBlank(message = "Description required")
    private String description;
   @NotBlank(message = "cover Image required")
    private String coverImage;
}
