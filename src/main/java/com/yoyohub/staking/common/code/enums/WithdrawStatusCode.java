package com.yoyohub.staking.common.code.enums;

import com.yoyohub.staking.common.code.EnumMapperType;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum WithdrawStatusCode implements EnumMapperType {
    NOT_APPLICABLE(0, "해당없음"),
    WAIT(1, "대기"),
    COMPLETE(2, "완료"),
    CANCEL(3, "취소");

    @Getter private int code;
    @Getter private String desc;

    WithdrawStatusCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static final Map<Integer, WithdrawStatusCode> COMMON_CODE_ENUMS =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(WithdrawStatusCode::getCode, Function.identity())));

    public static WithdrawStatusCode find(int code) {
        return COMMON_CODE_ENUMS.getOrDefault(code, NOT_APPLICABLE);
    }
}
