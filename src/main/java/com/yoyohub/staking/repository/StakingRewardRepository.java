package com.yoyohub.staking.repository;

import com.yoyohub.staking.entity.StakingReward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StakingRewardRepository extends JpaRepository<StakingReward, Integer> {

}
