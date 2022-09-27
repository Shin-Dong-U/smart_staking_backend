package com.yoyohub.staking.repository;

import com.yoyohub.staking.entity.RefreshToken;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest("spring.profiles.active:dev")
class RefreshTokenRepositoryTest {

    @Mock private RefreshTokenRepository refreshTokenRepo;

    @Test
    void findByUserIdAndToken() {
        String user = "test_user";
        String refreshToken = "eyJyZWdEYXRlIjoxNjY0MTgyOTcyODg4LCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoi6rSA66as7J6QIiwiaWQiOiJhc2RmIiwiZXhwIjoxNjY0MjY5MzcyfQ.hFzbjYlQr32ZKcYA5jhVXpU1yAWdunKlAT18l0cRxUU";
        RefreshToken expectedToken = RefreshToken.builder()
                .userId(user)
                .token(refreshToken)
                .build();

        when(refreshTokenRepo.findByUserIdAndToken(user, refreshToken)).thenReturn(expectedToken);

        RefreshToken token1 = refreshTokenRepo.findByUserIdAndToken(user, "none");
        assertNull(token1);

        RefreshToken token2 = refreshTokenRepo.findByUserIdAndToken(user, refreshToken);
        assertEquals(expectedToken, token2);
    }
}