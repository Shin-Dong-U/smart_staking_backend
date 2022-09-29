package com.yoyohub.staking.user;

import com.yoyohub.staking.entity.RewardClickHistory;
import com.yoyohub.staking.repository.RewardClickHistoryRepository;
import com.yoyohub.staking.security.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

import static com.yoyohub.staking.common.CommonUtil.isNullOrEmpty;

@Service
public class RewardClickServiceImpl implements RewardClickService {

    @Autowired private JwtManager jwtManager;
    @Autowired private RewardClickHistoryRepository rewardRepo;

    @Override
    public ResponseEntity<Void> save(HttpServletRequest request) {
        String userId = jwtManager.getId(request);

        if(isNullOrEmpty(userId)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Date today = Date.valueOf(java.time.LocalDate.now());
        RewardClickHistory clickHistory = rewardRepo.findByUserIdAndRegisteredDate(userId, today);

        if(clickHistory != null) {
            return ResponseEntity.ok().build();
        }

        RewardClickHistory rewardClickHistory = RewardClickHistory.builder()
                .userId(userId)
                .registeredDate(today)
                .build();
        rewardRepo.save(rewardClickHistory);


        return ResponseEntity.ok().build();
    }
}
