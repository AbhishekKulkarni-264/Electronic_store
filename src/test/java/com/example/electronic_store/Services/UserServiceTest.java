package com.example.electronic_store.Services;

import com.example.electronic_store.dtos.UserDto;
import com.example.electronic_store.entities.User;
import com.example.electronic_store.repositories.UserRepository;
import com.example.electronic_store.services.UserService;
import com.example.electronic_store.services.impl.UserServiceimpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@SpringBootTest
public class UserServiceTest {
    //Create user
    @Mock
    private UserRepository userRepository;

   @Autowired
    private ModelMapper mapper;

    User user;


    @Autowired
    private UserService userService;

    @BeforeEach
    public  void init(){
       user=User.builder()
                .name("Abhishek")
                .email("abhi@gmail.com")
                .about("Testing create")
                .gender("Male")
                .password("12345").build();
    }

    @Test
    public void createUserTest(){
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

       UserDto userDto=userService.createUser(mapper.map(user, UserDto.class));
       Assertions.assertNotNull(userDto);

    }
}
