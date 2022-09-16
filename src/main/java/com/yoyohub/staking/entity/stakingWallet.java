package com.yoyohub.staking.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Entity
public class stakingWallet {

    @Id
    @Column(name = "staking_wallet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;

    private BigDecimal totalYcoin;

    @Builder
    public stakingWallet(String userId, BigDecimal totalYcoin) {
        this.userId = userId;
        this.totalYcoin = totalYcoin;
    }
}
