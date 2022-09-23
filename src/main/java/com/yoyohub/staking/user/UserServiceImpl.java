package com.yoyohub.staking.user;

import com.yoyohub.staking.entity.User;
import com.yoyohub.staking.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserRepository userRepo;

    @Override
    public ResponseEntity<String> register(User user) {
        String message = "";

        // 1. 사용중인 아이디인지 확인
        int count = userRepo.countById(user.getId());
        if (count > 0) {
            message = "이미 사용중인 아이디입니다.";
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }

        // 2. 기존 가입 회원인지 확인
        User alreadyUser = userRepo.findByCi(user.getCi());
        if(alreadyUser != null) {
            message = "이미 가입된 회원입니다. 가입 아이디 [" + alreadyUser.getId() + "]";
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }

        // 3. 회원가입
        user.setRegisteredDate(LocalDateTime.now());
        userRepo.save(user);

        return ResponseEntity.ok("success");
    }

    @Override
    public User login(String username, String password) {
        return null;
    }
}
