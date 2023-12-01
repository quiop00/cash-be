package com.ryu.tobybe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.ryu.tobybe", "com.ryu.common"})
@EnableJpaRepositories(basePackages = {"com.ryu.tobybe.repositories", "com.ryu.common.repository"})
@EntityScan(basePackages = {"com.ryu.tobybe", "com.ryu.common"})
public class TobyBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TobyBeApplication.class, args);
	}

}
