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
public class YolifeWalletDetail {

    @Id
    @Column(name = "yolife_wallet_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;

    private int side;

    private BigDecimal ycoin;

    private int withdrawType;

    private LocalDateTime modifiedDate;

    private LocalDateTime registeredDate;

    @Builder
    public YolifeWalletDetail(String userId, int side, BigDecimal ycoin, int withdrawType, LocalDateTime modifiedDate, LocalDateTime registeredDate) {
        this.userId = userId;
        this.side = side;
        this.ycoin = ycoin;
        this.withdrawType = withdrawType;
        this.modifiedDate = modifiedDate;
        this.registeredDate = registeredDate;
    }
}
