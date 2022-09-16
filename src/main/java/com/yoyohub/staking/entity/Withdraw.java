package com.yoyohub.staking.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Withdraw {

    @Id
    @Column(name = "withdraw_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;

    private int goldWalletDetailId;

    private int amount;

    private int type;

    private int status;

    private LocalDateTime processedDate;

    private LocalDateTime modifiedDate;

    private LocalDateTime registeredDate;

    @Builder
    public Withdraw(String userId, int goldWalletDetailId, int amount, int type, int status, LocalDateTime processedDate, LocalDateTime modifiedDate, LocalDateTime registeredDate) {
        this.userId = userId;
        this.goldWalletDetailId = goldWalletDetailId;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.processedDate = processedDate;
        this.modifiedDate = modifiedDate;
        this.registeredDate = registeredDate;
    }
}
