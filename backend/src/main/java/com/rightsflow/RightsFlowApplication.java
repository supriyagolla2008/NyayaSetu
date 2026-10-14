package com.rightsflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RightsFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(RightsFlowApplication.class, args);
    }
}
