package com.margolin.project.servicemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

@SpringBootApplication(exclude = RedisAutoConfiguration.class)
public class ServiceManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceManagerApplication.class, args);
    }

}
