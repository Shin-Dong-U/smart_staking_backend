package com.yoyohub.staking.common.code;

import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum StakingStatusCode implements EnumMapperType {

    ACTIVE(0, "활성화"),
    CANCELED(1, "취소"),
    COMPLETE(2, "완료");

    @Getter private int code;
    @Getter private String desc;

    StakingStatusCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static Map<Integer, StakingStatusCode> COMMON_CODE_ENUMS = Stream.of(values())
            .collect(Collectors.toMap(StakingStatusCode::getCode, Function.identity()));

    public static StakingStatusCode find(int code) {
        return COMMON_CODE_ENUMS.getOrDefault(code, ACTIVE);
    }
}
