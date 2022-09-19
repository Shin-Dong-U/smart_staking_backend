package com.yoyohub.staking.common.code;

public enum GoldWalletWithdrawTypeCode implements EnumMapperType {
    NOT_APPLICABLE(0, "해당없음"),
    WITHDRAW_REQUEST(1, "출금요청"),
    WITHDRAW_COMPLETE(2, "출금완료"),
    WITHDRAW_CANCEL(3, "출금취소"),
    WITHDRAW_REJECT(4, "출금요청반려"),
    RE_STAKING_REQUEST(5, "리스테이킹요청"),
    RE_STAKING_CANCEL(6, "리스테이킹취소"),
    RE_STAKING_COMPLETE(6, "리스테이킹완료");

    private int code;
    private String desc;

    GoldWalletWithdrawTypeCode(int code, String desc) {
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

}
