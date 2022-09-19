package com.yoyohub.staking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StakingApplication {

    public static void main(String[] args) {
        String profile = System.getProperty("spring.profiles.active");

        if(profile == null)
            System.setProperty("spring.profiles.active", "dev");

        SpringApplication.run(StakingApplication.class, args);
    }

}
