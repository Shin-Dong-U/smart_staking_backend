package com.yoyohub.staking.common.code.enums;

import com.yoyohub.staking.common.code.EnumMapperType;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DepositStatusCode implements EnumMapperType {
    NOT_APPLICABLE(0, "해당없음"),
    DEPOSIT_REQUEST(1, "입금요청"),
    DEPOSIT_COMPLETE(2, "입금완료"),
    DEPOSIT_CANCEL(3, "입금취소"),
    DEPOSIT_REJECT(4, "입금요청반려");

    @Getter private int code;
    @Getter private String desc;

    DepositStatusCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static final Map<Integer, DepositStatusCode> COMMON_CODE_ENUMS =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(DepositStatusCode::getCode, Function.identity())));

    public static DepositStatusCode find(int code) {
        return COMMON_CODE_ENUMS.getOrDefault(code, NOT_APPLICABLE);
    }
}
