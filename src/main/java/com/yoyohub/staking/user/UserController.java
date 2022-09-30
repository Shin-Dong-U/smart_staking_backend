package com.yoyohub.staking.user;

import com.yoyohub.staking.entity.User;
import com.yoyohub.staking.security.JwtManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired private UserService userService;
    @Autowired private JwtManager jwtManager;

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

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<User> login(HttpServletResponse response, @RequestBody User loginInfo) {
        User user = userService.login(loginInfo);

        if(user == null) {
            return ResponseEntity.status(401).body(null);
        }

        jwtManager.issueToken(response, user);

        return ResponseEntity.ok(user);
    }

    @ResponseBody
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        jwtManager.removeAllTokenCookies(response);
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("admin");
    }
}
