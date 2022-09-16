package com.yoyohub.staking.entity;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class StakingDetail {

    @Id
    @Column(name = "staking_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;

    private int depositId;

    private int goldWalletDetailId;

    private int grade;

    private BigDecimal ycoin;

    private int remaingTimes;

    private int status;

    private LocalDateTime modifiedDate;

    private LocalDateTime registeredDate;

    @Builder
    public StakingDetail(String userId, int depositId, int goldWalletDetailId, int grade, BigDecimal ycoin, int remaingTimes, int status, LocalDateTime modifiedDate, LocalDateTime registeredDate) {
        this.userId = userId;
        this.depositId = depositId;
        this.goldWalletDetailId = goldWalletDetailId;
        this.grade = grade;
        this.ycoin = ycoin;
        this.remaingTimes = remaingTimes;
        this.status = status;
        this.modifiedDate = modifiedDate;
        this.registeredDate = registeredDate;
    }
}
