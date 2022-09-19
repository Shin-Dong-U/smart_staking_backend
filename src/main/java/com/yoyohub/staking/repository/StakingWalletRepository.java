package com.yoyohub.staking.repository;

import com.yoyohub.staking.entity.StakingWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StakingWalletRepository extends JpaRepository<StakingWallet, Integer> {

}
