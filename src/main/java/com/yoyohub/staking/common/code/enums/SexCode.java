package com.yoyohub.staking.common.code.enums;

import com.yoyohub.staking.common.code.EnumMapperType;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SexCode implements EnumMapperType {
    NOT_APPLICABLE(0, "해당없음"),
    MALE(1, "남자"),
    FEMALE(2, "여자");

    @Getter private int code;
    @Getter private String desc;

    SexCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static final Map<Integer, SexCode> COMMON_CODE_ENUMS =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(SexCode::getCode, Function.identity())));

    public static SexCode find(int code) {
        return COMMON_CODE_ENUMS.getOrDefault(code, NOT_APPLICABLE);
    }

    public static SexCode findByNhnCode(String code) {
        int intCode = 0;
        try { intCode = Integer.parseInt(code); } catch (Exception e) { }

        return find(intCode);
    }
}
