package com.yoyohub.staking.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class GoldWalletDetail {

    @Id
    @Column(name = "gold_wallet_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;

    private int staking_reward_id;

    private int side;

    private BigDecimal ycoin;

    private int withdrawType;

    private LocalDateTime modifiedDate;

    private LocalDateTime registeredDate;

    @Builder
    public GoldWalletDetail(String userId, int staking_reward_id, int side, BigDecimal ycoin, int withdrawType, LocalDateTime modifiedDate, LocalDateTime registeredDate) {
        this.userId = userId;
        this.staking_reward_id = staking_reward_id;
        this.side = side;
        this.ycoin = ycoin;
        this.withdrawType = withdrawType;
        this.modifiedDate = modifiedDate;
        this.registeredDate = registeredDate;
    }
}
