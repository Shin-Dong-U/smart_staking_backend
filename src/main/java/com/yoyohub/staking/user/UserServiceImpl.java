package com.yoyohub.staking.user;

import com.yoyohub.staking.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean register(User user) {
        return false;
    }

    @Override
    public User login(String username, String password) {
        return null;
    }
}
