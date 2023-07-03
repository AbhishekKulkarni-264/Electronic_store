package com.example.electronic_store.services.impl;

import com.example.electronic_store.dtos.UserDto;
import com.example.electronic_store.entities.User;
import com.example.electronic_store.exceptions.ResourceNotFoundException;
import com.example.electronic_store.repositories.UserRepository;
import com.example.electronic_store.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        String UserId=UUID.randomUUID().toString();
        userDto.setUserId(UserId);
        User user=dtoToEntity(userDto);
        User savedUser= userRepository.save(user);
        UserDto newDto= entityToDto(savedUser);
        return newDto;
    }

    private UserDto entityToDto(User savedUser) {
//       UserDto userDto= UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .password(savedUser.getPassword())
//                .about(savedUser.getAbout())
//                .email(savedUser.getEmail())
//                .gender(savedUser.getGender())
//                .imageName(savedUser.getImageName()).build();

       return  mapper.map(savedUser,UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {
//       User user=User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//               .imageName(userDto.getImageName()).build();
        return mapper.map(userDto,User.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String UserId) {
       User user=userRepository.findById(UserId).orElseThrow(()->new ResourceNotFoundException("User not found !!"));
       user.setName(userDto.getName());
       user.setEmail(userDto.getEmail());
       user.setPassword(userDto.getPassword());
       user.setAbout(userDto.getAbout());
       user.setGender(userDto.getGender());
//       user.setImageName(userDto.getImageName());

      User updatedUser=userRepository.save(user);
      UserDto updatedDto= entityToDto(updatedUser);
        return updatedDto;
    }

    @Override
    public void deleteUser(String UserId) {
        User user=userRepository.findById(UserId).orElseThrow(()->new ResourceNotFoundException("User not found !!"));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUser(int pageNumber,int pageSize) {
       List<User> users=userRepository.findAll();
       List<UserDto>dtoList= users.stream().map(user->entityToDto(user)).collect(Collectors.toList());
       return dtoList;
    }

    @Override
    public UserDto getUserByEmail(String email) {
       User user= userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found with this email"));
        return entityToDto(user);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found !!"));
        UserDto userDto=entityToDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> searchUser(String Keyword) {
      List<User>users=  userRepository.findByNameContaining(Keyword).orElseThrow(()->new ResourceNotFoundException("Users with this not found !!"));
      List<UserDto>dtoList= users.stream().map(user->entityToDto(user)).collect(Collectors.toList());
      return dtoList;
    }
}
