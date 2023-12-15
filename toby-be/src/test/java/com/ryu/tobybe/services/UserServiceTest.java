package com.ryu.tobybe.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ryu.common.entity.UserEntity;
import com.ryu.common.repository.UserRepository;
import com.ryu.tobybe.models.UserDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(UserEntity
                        .builder()
                        .id(1)
                        .email("test@gmail.com")
                        .build()));
        Mockito.when(userRepository.findById(2L))
                .thenReturn(Optional.empty());
    }

    @Test
    @DisplayName("Get user with not exists Id")
    public void testGetUserThrowException() {
        Exception exception = assertThrows(Exception.class, () -> {
            userService.getUser(2L);
        });
        Assert.assertEquals("USER NOT FOUND", exception.getMessage());
    }

    @Test
    @DisplayName("Get user with exists Id user")
    public void testGetUserById() {
        try {
            UserDto res = userService.getUser(1);
            Assert.assertEquals(res.getId(), 1);
            Assert.assertEquals(res.getEmail(), "test@gmail.com");
        } catch (Exception e) {

        }
    }
}
