package com.yoyohub.staking.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class RewardClickHistory {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;

    private LocalDateTime registeredDate;

    @Builder
    public RewardClickHistory(String userId, LocalDateTime registeredDate) {
        this.userId = userId;
        this.registeredDate = registeredDate;
    }
}
