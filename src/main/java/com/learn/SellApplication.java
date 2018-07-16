package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class SellApplication {

        // 我在这里做了改动，一会解决冲突
	public static void main(String[] args) {
		SpringApplication.run(SellApplication.class, args);
	}
}

