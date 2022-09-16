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
public class StakingReward {

    @Id
    @Column(name = "staking_reward_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int stakingDetailId;

    private String userId;

    private BigDecimal totalYcoin;

    private BigDecimal goldYcoin;

    private BigDecimal yolifeYcoin;

    private int times;

    private LocalDateTime modifiedDate;

    private LocalDateTime registeredDate;

    @Builder
    public StakingReward(int stakingDetailId, String userId, BigDecimal totalYcoin, BigDecimal goldYcoin, BigDecimal yolifeYcoin, int times, LocalDateTime modifiedDate, LocalDateTime registeredDate) {
        this.stakingDetailId = stakingDetailId;
        this.userId = userId;
        this.totalYcoin = totalYcoin;
        this.goldYcoin = goldYcoin;
        this.yolifeYcoin = yolifeYcoin;
        this.times = times;
        this.modifiedDate = modifiedDate;
        this.registeredDate = registeredDate;
    }
}
