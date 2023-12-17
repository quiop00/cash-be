package com.ryu.tobybe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ryu.tobybe.models.UserDto;
import com.ryu.tobybe.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void testGetAllUsersSuccess() throws Exception {
        List<UserDto> users = Arrays.asList(1, 2, 3, 4, 5)
                .stream().map(e -> UserDto.builder()
                        .id(e)
                        .email("user" + e + "@gmail.com")
                        .username("user" + e)
                        .build())
                .toList();

        Mockito.when(userService.getVerifyUsers()).thenReturn(users);
        // Call the controller method
        ResponseEntity<?> responseEntity = userController.getVerifyUsers();

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
