package com.yoyohub.staking.common.code.enums;

import com.yoyohub.staking.common.code.EnumMapperType;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum WalletSideCode implements EnumMapperType {
    NOT_APPLICABLE(0, "해당없음"),
    DEPOSIT(1, "입금"),
    WITHDRAW(2, "출금");

    @Getter private int code;
    @Getter private String desc;

    WalletSideCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static final Map<Integer, WalletSideCode> COMMON_CODE_ENUMS =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(WalletSideCode::getCode, Function.identity())));

    public static WalletSideCode find(int code) {
        return COMMON_CODE_ENUMS.getOrDefault(code, NOT_APPLICABLE);
    }
}
