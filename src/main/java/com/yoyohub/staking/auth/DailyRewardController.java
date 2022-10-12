package com.yoyohub.staking.auth;

import com.yoyohub.staking.user.RewardClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DailyRewardController {

    @Autowired private RewardClickService rewardClickService;

    @ResponseBody
    @GetMapping("/auth/daily-reward")
    public ResponseEntity<Void> dailyReward(HttpServletRequest request) {
        return rewardClickService.save(request);
    }
}
