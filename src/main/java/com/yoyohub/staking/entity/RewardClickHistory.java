package com.yoyohub.staking.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
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

    private Date registeredDate;

    @Builder
    public RewardClickHistory(String userId, Date registeredDate) {
        this.userId = userId;
        this.registeredDate = registeredDate;
    }
}
