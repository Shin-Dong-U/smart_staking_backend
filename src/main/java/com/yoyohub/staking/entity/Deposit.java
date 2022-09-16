package com.yoyohub.staking.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Deposit {

    @Id
    @Column(name = "deposit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;

    private int amount;

    private int status;

    private LocalDateTime processedDate;

    private LocalDateTime modifiedDate;

    private LocalDateTime registeredDate;

    @Builder
    public Deposit(String userId, int amount, int status, LocalDateTime processedDate, LocalDateTime modifiedDate, LocalDateTime registeredDate) {
        this.userId = userId;
        this.amount = amount;
        this.status = status;
        this.processedDate = processedDate;
        this.modifiedDate = modifiedDate;
        this.registeredDate = registeredDate;
    }
}
