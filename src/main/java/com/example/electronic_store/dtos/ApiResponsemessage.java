package com.example.electronic_store.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponsemessage {
    private String message;
    private boolean success;
    private HttpStatus status;

}
