package com.tjc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringbootForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootForumApplication.class, args);
    }

}
