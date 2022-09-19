package com.yoyohub.staking.common;

import com.yoyohub.staking.common.code.enums.UserGrade;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UserGradeTest {

    @Test
    void findByCoinAndDeposit() {
        // 현재 등급 X
        UserGrade x0 = UserGrade.findByCoinAndDeposit(new BigDecimal(887), 1200);
        assertEquals(UserGrade.NO_POSITION, x0);

        UserGrade x1 = UserGrade.findByCoinAndDeposit(new BigDecimal(-1), 1154400);
        assertEquals(UserGrade.NO_POSITION, x1);

        UserGrade x2 = UserGrade.findByCoinAndDeposit(new BigDecimal(887), 1300);
        assertEquals(UserGrade.SANGSA, x2);

        UserGrade x3 = UserGrade.findByCoinAndDeposit(new BigDecimal(887.999), 2308800);
        assertEquals(UserGrade.TEAMJANG, x3);

        UserGrade x4 = UserGrade.findByCoinAndDeposit(new BigDecimal(0), 4804800);
        assertEquals(UserGrade.GOOKJANG, x4);

        UserGrade x5 = UserGrade.findByCoinAndDeposit(new BigDecimal(887), 10010000);
        assertEquals(UserGrade.VONBUJANG, x5);

        UserGrade x6 = UserGrade.findByCoinAndDeposit(new BigDecimal(887), 20800000);
        assertEquals(UserGrade.SANGMU, x6);

        UserGrade x7 = UserGrade.findByCoinAndDeposit(new BigDecimal(887), 41600000);
        assertEquals(UserGrade.CHONGJANG, x7);

        UserGrade x8 = UserGrade.findByCoinAndDeposit(new BigDecimal(887), 83200000);
        assertEquals(UserGrade.CROWN, x8);

        UserGrade x9 = UserGrade.findByCoinAndDeposit(new BigDecimal(887), 223600000);
        assertEquals(UserGrade.ROYAL_CROWN, x9);

        // 현재 등급 O
        UserGrade o1 = UserGrade.findByCoinAndDeposit(new BigDecimal(888), 0);
        assertEquals(UserGrade.SANGSA, o1);

        UserGrade o2 = UserGrade.findByCoinAndDeposit(new BigDecimal(888), 2100000000);
        assertEquals(UserGrade.SANGSA, o2);

        UserGrade o3 = UserGrade.findByCoinAndDeposit(new BigDecimal(1776.888), 2100000000);
        assertEquals(UserGrade.TEAMJANG, o3);
    }
}