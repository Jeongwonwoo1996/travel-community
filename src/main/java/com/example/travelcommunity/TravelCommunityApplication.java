package com.example.travelcommunity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.travelcommunity.mapper")
public class TravelCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelCommunityApplication.class, args);
	}

}
