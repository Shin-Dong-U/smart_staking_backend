package com.yoyohub.staking.common.code;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum LocalCode implements EnumMapperType {
    NOT_APPLICABLE(0, "해당없음"),
    LOCAL(1, "내국인"),
    FOREIGNER(2, "외국인");

    private int code;
    private String desc;

    LocalCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    private static final Map<Integer, LocalCode> COMMON_CODE_ENUMS =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(LocalCode::getCode, Function.identity())));

    public static LocalCode find(int code) {
        return COMMON_CODE_ENUMS.getOrDefault(code, NOT_APPLICABLE);
    }

    public static LocalCode findByNhnCode(String code) {
        int intCode = 0;
        try { intCode = Integer.parseInt(code); } catch (Exception e) { }

        return find(intCode);
    }
}
