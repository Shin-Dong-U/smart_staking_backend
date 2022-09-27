package com.yoyohub.staking.repository;

import com.yoyohub.staking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    int countById(String id);
    User findByCi(String ci);
}
