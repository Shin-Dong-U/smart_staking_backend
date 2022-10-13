package com.yoyohub.staking;

import com.yoyohub.staking.etc.Prayer;
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

        Prayer.print();

        SpringApplication.run(StakingApplication.class, args);
    }

}
