package com.ryu.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.ryu.auth", "com.ryu.common"})
@EnableJpaRepositories(basePackages = {"com.ryu.auth.repositories", "com.ryu.common.repository"})
@EntityScan(basePackages = {"com.ryu.auth", "com.ryu.common"})
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
