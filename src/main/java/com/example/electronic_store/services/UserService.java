package com.example.electronic_store.services;

import com.example.electronic_store.dtos.UserDto;

import java.util.List;


public interface UserService {
    //create

    UserDto createUser(UserDto user);
    //update
    UserDto updateUser(UserDto userDto,String UserId);
    //delete

    void deleteUser(String UserId);
    //get all user
    List<UserDto> getAllUser(int pageNumber,int pageSize);

    //get Single user by email

    UserDto getUserByEmail(String email);

    //get user by id
    UserDto getUserById(String userId);

    //search user

    List<UserDto> searchUser(String Keyword);

}
