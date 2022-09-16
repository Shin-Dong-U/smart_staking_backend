package com.yoyohub.staking.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Entity
public class GoldWallet {

    @Id
    @Column(name = "gold_wallet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;

    private BigDecimal totalYcoin;

    @Builder
    public GoldWallet(String userId, BigDecimal totalYcoin) {
        this.userId = userId;
        this.totalYcoin = totalYcoin;
    }
}
