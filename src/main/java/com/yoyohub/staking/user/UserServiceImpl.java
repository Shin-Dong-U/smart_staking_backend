package com.yoyohub.staking.user;

import static com.yoyohub.staking.common.CommonUtil.*;

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

        // 3. 비밀번호 암호화
        String password = encrypt(user.getPassword(), getSalt());
        String password2 = encrypt(user.getPassword2(), getSalt());
        user.setPassword(password);
        user.setPassword2(password2);

        // 4. 회원가입
        user.setRegisteredDate(LocalDateTime.now());
        userRepo.save(user);

        return ResponseEntity.ok("success");
    }

    @Override
    public User login(User loginInfo) {
        if(!validLoginInfo(loginInfo)) { return null; }

        // 1. ID로 DB 조회
        User dbuser = userRepo.findById(loginInfo.getId()).orElse(null);

        // 2. 유저가 없다면 null 반환
        if(!validLoginInfo(dbuser)) { return null; }

        // 3. 비밀번호 확인
        String dbPwd = dbuser.getPassword();
        String loginPwd = encrypt(loginInfo.getPassword(), getSalt());
        boolean isMatch = dbPwd.equals(loginPwd);

        // 4. 비밀번호가 일치하지 않는다면 null 반환
        if(!isMatch) { return null; }

        return dbuser;
    }

    private boolean validLoginInfo(User loginInfo) {
        if(isNullOrEmpty(loginInfo, loginInfo.getId(), loginInfo.getPassword())){
            return false;
        }

        return true;
    }

}
