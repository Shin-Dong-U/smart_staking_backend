package com.yoyohub.staking.common.code.enums;

import lombok.Getter;

public enum TokenType {
    ACCESS_TOKEN("access_token", 1000L * 60 * 60, 60 * 60), // 유효시간 1시간
    REFRESH_TOKEN("refresh_token", 1000L * 60 * 60 * 24 * 14, 60 * 60 * 24 * 14); // 유효시간 14일

    @Getter private String desc;
    @Getter private long expiredMillisecond;
    @Getter private int expiredSecond;

    TokenType(String desc, long expiredMillisecond, int expiredSecond) {
        this.desc = desc;
        this.expiredMillisecond = expiredMillisecond;
        this.expiredSecond = expiredSecond;
    }
}
