package com.yoyohub.staking.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest("spring.profiles.active:dev")
class JwtManagerTest {

    @Autowired private JwtManager jwtManager;

    @Test
    void issueAccessToken() {
    }

    @Test
    void issueRefreshToken() {
    }

    @Test
    void isValid() {
    }

    @Test
    void isExpired() {
        long currTime = System.currentTimeMillis();
        final int DELAY = 1000;

        Date currDate = new Date(currTime + DELAY);
        Date expDate = new Date(currTime - 10000);

        assertFalse(jwtManager.isExpired(currDate));
        assertTrue(jwtManager.isExpired(expDate));
    }
}