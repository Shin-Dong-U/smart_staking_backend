package com.yoyohub.staking.user;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface RewardClickService {
    ResponseEntity<Void> save(HttpServletRequest request);
}
