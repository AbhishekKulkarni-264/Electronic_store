package com.example.electronic_store.dtos;

import com.example.electronic_store.validate.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;
    @Size(min=3,max=15,message = "Invalid name !!")
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",message = "Invalid email")
    @NotBlank(message = "email cannot be blank")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @Size(min = 4,max = 6,message = "Invalid gender")
    private String gender;
    @NotBlank(message = "About cannot be empty")
    private String about;
//    @ImageNameValid
//    private String imageName;
}
