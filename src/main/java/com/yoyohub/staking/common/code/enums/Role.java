package com.yoyohub.staking.common.code.enums;

import com.yoyohub.staking.common.code.EnumMapperType;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role implements EnumMapperType {
    USER(0, "일반회원"),
    MANAGER(10, "매니저"),
    ADMIN(100, "관리자");

    @Getter private int code;
    @Getter private String desc;

    Role(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static final Map<Integer, Role> COMMON_CODE_ENUMS =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Role::getCode, Function.identity())));

    public static Role find(int code) {
        return COMMON_CODE_ENUMS.getOrDefault(code, USER);
    }
}
