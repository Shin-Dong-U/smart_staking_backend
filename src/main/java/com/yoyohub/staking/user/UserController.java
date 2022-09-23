package com.yoyohub.staking.user;

import com.yoyohub.staking.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired private UserService userService;

    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            return userService.register(user);
        } catch (Exception e) {
            log.error("회원가입 데이터 삽입 실패.\n" + user.toString(), e);
            return ResponseEntity.status(500).body("회원가입 실패");
        }
    }
}
