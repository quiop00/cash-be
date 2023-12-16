package com.ryu.tobybe.repositories;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ryu.common.entity.UserEntity;
import com.ryu.common.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // Clear the repository before each test
        userRepository.deleteAll();
    }
    
    @Test
    public void testSave() {
        userRepository.save(UserEntity.builder()
                            .id(1)
                            .email("mhoangduc08@gmail.com")
                            .username("mhoangduc08")
                            .password("12345678")
                            .notifications(new ArrayList<>())
                            .paymentRequests(new ArrayList<>())
                            .build());
        userRepository.save(UserEntity.builder()
                            .id(2)
                            .email("mhoangduc25@gmail.com")
                            .username("mhoangduc25")
                            .password("12345678")
                            .notifications(new ArrayList<>())
                            .paymentRequests(new ArrayList<>())
                            .build());
        userRepository.findAll().forEach(e -> System.out.println(e.getEmail()));

        Assertions.assertThat(userRepository.findAll()).hasSize(2);
    }

    @After
    public void clean() {
        // Clear the repository before each test
        userRepository.deleteAll();
    }

    
}
