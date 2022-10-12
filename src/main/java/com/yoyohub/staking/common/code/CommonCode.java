package com.yoyohub.staking.common.code;

import com.yoyohub.staking.common.code.enums.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommonCode {

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();

        enumMapper.put("BANK_CODE", BankCode.class);
        enumMapper.put("DEPOSIT_STATUS_CODE", DepositStatusCode.class);
        enumMapper.put("GOLD_WALLET_WITHDRAW_TYPE_CODE", GoldWalletWithdrawTypeCode.class);
        enumMapper.put("LOCAL_CODE", LocalCode.class);
        enumMapper.put("SEX_CODE", SexCode.class);
        enumMapper.put("STAKING_STATUS_CODE", StakingStatusCode.class);
        enumMapper.put("USER_GRADE", UserGrade.class);
        enumMapper.put("WALLET_SIDE_CODE", WalletSideCode.class);
        enumMapper.put("WITHDRAW_STATUS_CODE", WithdrawStatusCode.class);

        return enumMapper;
    }
}
