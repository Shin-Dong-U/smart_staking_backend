package com.yoyohub.staking.common.code;

import lombok.Getter;

public class EnumMapperValue {

    @Getter private int code;
    @Getter private String desc;

    public EnumMapperValue(EnumMapperType enumMapperType) {
        this.code = enumMapperType.getCode();
        this.desc = enumMapperType.getDesc();
    }

    @Override
    public String toString() {
        return "{\"code\":" + code + ",\"desc\":\"" + desc + "\"}";
    }
}
