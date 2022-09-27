package com.yoyohub.staking.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class RefreshToken {

    @Id
    private String userId;
    private String token;

    @Builder
    public RefreshToken(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
