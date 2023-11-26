package com.ryu.tobybe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.ryu.toby-be", "com.ryu.common"})
@EntityScan(basePackages = {"com.ryu.toby-be", "com.ryu.common"})
public class TobyBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TobyBeApplication.class, args);
	}

}
