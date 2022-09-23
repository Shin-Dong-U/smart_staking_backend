package com.yoyohub.staking.user;

import com.yoyohub.staking.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<String> register(User user);
    public User login(String username, String password);
}
