package com.yoyohub.staking.user;

import com.yoyohub.staking.entity.User;

public interface UserService {
    public boolean register(User user);
    public User login(String username, String password);
}
