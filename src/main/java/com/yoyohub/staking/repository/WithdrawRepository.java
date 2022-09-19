package com.yoyohub.staking.repository;

import com.yoyohub.staking.entity.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRepository extends JpaRepository<Withdraw, Integer> {

}
