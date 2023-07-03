package com.example.electronic_store.controllers;

import com.example.electronic_store.dtos.ApiResponsemessage;
import com.example.electronic_store.dtos.UserDto;
import com.example.electronic_store.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    //create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

       UserDto user= userService.createUser(userDto);
       return  new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") String userId){
        UserDto user= userService.updateUser(userDto,userId);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponsemessage> deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
       ApiResponsemessage message= ApiResponsemessage.builder().message("User deleted Successfully").success(true).status(HttpStatus.OK).build();
        return  new ResponseEntity<>(message,HttpStatus.NO_CONTENT);
    }

    //Get all
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
                                                     @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize){
        return new ResponseEntity<>(userService.getAllUser(pageNumber,pageSize),HttpStatus.OK);
    }

@GetMapping("/{userId}")
   public ResponseEntity<UserDto> getUserbyId(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
}

@GetMapping("/email/{email}")
public  ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email){
        return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
}

    @GetMapping("/search/{keywords}")
    public  ResponseEntity<List<UserDto>> searchUser(@PathVariable("keywords") String keywords){
        return new ResponseEntity<>(userService.searchUser(keywords),HttpStatus.OK);
    }


}
