package com.yoyohub.staking.common.code;

import lombok.Getter;

public enum WithdrawStatusCode implements EnumMapperType {
    WAIT(0, "대기"),
    COMPLETE(1, "완료"),
    CANCEL(2, "취소");

    @Getter private int code;
    @Getter private String desc;

    WithdrawStatusCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
