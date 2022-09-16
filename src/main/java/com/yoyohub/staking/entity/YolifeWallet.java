package com.yoyohub.staking.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Entity
public class YolifeWallet {

    @Id
    @Column(name = "yolife_wallet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;

    private BigDecimal totalYolife;

    @Builder
    public YolifeWallet(String userId, BigDecimal totalYolife) {
        this.userId = userId;
        this.totalYolife = totalYolife;
    }
}
