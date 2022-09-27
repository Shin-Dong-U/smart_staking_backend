package com.yoyohub.staking.repository;

import com.yoyohub.staking.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    RefreshToken findByUserIdAndToken(String userId, String token);
}
