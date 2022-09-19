package com.yoyohub.staking.common.code;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum BankCode implements EnumMapperType {
    NOT_APPLICABLE(0, "해당없음"),
    IBK_KIUP(3, "기업은행"),
    KOOKMIN(4, "국민은행"),
    HANA(5, "하나은행"),
    SUCHOOLIP(8, "수출입은행"),
    SUHYUP(9, "수협중앙회"),
    NONHYUP(10, "농협은행"),
    WOORI(20, "우리은행"),
    JEIL(23, "제일은행"),
    CITY(27, "씨티은행"),
    DEGU(31, "대구은행"),
    BUSAN(32, "부산은행"),
    SHINHAN(34, "신한은행"),
    SEMAUL(45, "새마을금고"),
    JUNBOOK(47, "전북은행"),
    KYUNGNAM(48, "경남은행"),
    SANGHO(50, "상호저축은행"),
    KBANK(53, "케이뱅크"),
    KAKAOBANK(90, "카카오뱅크");

    private int code;
    private String desc;

    BankCode(int code, String desc) {
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

    private static final Map<Integer, BankCode> COMMON_CODE_ENUMS =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(BankCode::getCode, Function.identity())));

    public static BankCode find(int code) {
        return COMMON_CODE_ENUMS.getOrDefault(code, NOT_APPLICABLE);
    }
}
