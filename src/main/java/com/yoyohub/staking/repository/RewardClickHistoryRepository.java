package com.yoyohub.staking.repository;

import com.yoyohub.staking.entity.RewardClickHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDateTime;

public interface RewardClickHistoryRepository extends JpaRepository<RewardClickHistory, Integer> {
    RewardClickHistory findByUserIdAndRegisteredDate(String userId, Date registeredDate);
}
