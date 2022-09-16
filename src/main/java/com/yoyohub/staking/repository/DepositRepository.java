package com.yoyohub.staking.repository;

import com.yoyohub.staking.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Integer> {
}
