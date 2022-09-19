package com.yoyohub.staking.common.code.enums;

import com.yoyohub.staking.common.code.EnumMapperType;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GoldWalletWithdrawTypeCode implements EnumMapperType {
    NOT_APPLICABLE(0, "해당없음"),
    WITHDRAW_REQUEST(1, "출금요청"),
    WITHDRAW_COMPLETE(2, "출금완료"),
    WITHDRAW_CANCEL(3, "출금취소"),
    WITHDRAW_REJECT(4, "출금요청반려"),
    RE_STAKING_REQUEST(5, "리스테이킹요청"),
    RE_STAKING_CANCEL(6, "리스테이킹취소"),
    RE_STAKING_COMPLETE(7, "리스테이킹완료");

    @Getter private int code;
    @Getter private String desc;

    GoldWalletWithdrawTypeCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static final Map<Integer, GoldWalletWithdrawTypeCode> COMMON_CODE_ENUMS =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(GoldWalletWithdrawTypeCode::getCode, Function.identity())));

    public static GoldWalletWithdrawTypeCode find(int code) {
        return COMMON_CODE_ENUMS.getOrDefault(code, NOT_APPLICABLE);
    }
}
