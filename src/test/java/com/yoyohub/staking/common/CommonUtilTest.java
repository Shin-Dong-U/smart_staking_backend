package com.yoyohub.staking.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CommonUtilTest {

    @Test
    void formatNumber() {
        assertEquals("001", CommonUtil.formatNumber(1, 3));
        assertEquals("011", CommonUtil.formatNumber(11, 3));
        assertEquals("111", CommonUtil.formatNumber(111, 3));
        assertEquals("777777777", CommonUtil.formatNumber(777777777, 3));
    }

    @Test
    void gt() {
        assertTrue(CommonUtil.gt(new BigDecimal("1.1"), new BigDecimal("1.0")));
        assertFalse(CommonUtil.gt(new BigDecimal("1.0"), new BigDecimal("1.0")));
        assertFalse(CommonUtil.gt(new BigDecimal("0.9"), new BigDecimal("1.0")));
    }

    @Test
    void gte() {
        assertTrue(CommonUtil.gte(new BigDecimal("1.1"), new BigDecimal("1.0")));
        assertTrue(CommonUtil.gte(new BigDecimal("1.0"), new BigDecimal("1.0")));
        assertFalse(CommonUtil.gte(new BigDecimal("0.9"), new BigDecimal("1.0")));
    }

    @Test
    void lt() {
        assertFalse(CommonUtil.lt(new BigDecimal("1.1"), new BigDecimal("1.0")));
        assertFalse(CommonUtil.lt(new BigDecimal("1.0"), new BigDecimal("1.0")));
        assertTrue(CommonUtil.lt(new BigDecimal("0.9"), new BigDecimal("1.0")));
    }

    @Test
    void lte() {
        assertFalse(CommonUtil.lte(new BigDecimal("1.1"), new BigDecimal("1.0")));
        assertTrue(CommonUtil.lte(new BigDecimal("1.0"), new BigDecimal("1.0")));
        assertTrue(CommonUtil.lte(new BigDecimal("0.9"), new BigDecimal("1.0")));
    }

    @Test
    void gteLt() {
        assertTrue(CommonUtil.gteLt(new BigDecimal("1.1"), new BigDecimal("1.0"), new BigDecimal("1.2")));
        assertTrue(CommonUtil.gteLt(new BigDecimal("1.0"), new BigDecimal("1.0"), new BigDecimal("1.2")));
        assertFalse(CommonUtil.gteLt(new BigDecimal("0.9"), new BigDecimal("1.0"), new BigDecimal("1.2")));
        assertFalse(CommonUtil.gteLt(new BigDecimal("1.2"), new BigDecimal("1.0"), new BigDecimal("1.2")));
    }

    @Test
    void getSalt() {
        String salt = CommonUtil.getSalt();
        assertEquals(20, salt.length());
    }


    @Test
    void encrypt() {
        String salt = "97448316";

        // case1. 같은 비밀번호, salt 테스트
        String pwd = "1234";
        String enc = CommonUtil.encrypt(pwd, salt);
        String eqTest = CommonUtil.encrypt(pwd, salt);
        assertEquals(enc, eqTest);

        // case2. 같은 비밀번호, salt 테스트 (특문포함)
        String pwd2 = "asdf1234@!";
        String enc2 = CommonUtil.encrypt(pwd2, salt);
        String symbolEqTest = CommonUtil.encrypt(pwd2, salt);
        assertEquals(enc2, symbolEqTest);

        // case3. 다른 비밀번호 테스트 (문자열 길이 동일)
        String pwd3 = "1234";
        String enc3 = CommonUtil.encrypt(pwd3, salt);
        String neTest = CommonUtil.encrypt("4321", salt);
        assertNotEquals(enc3, neTest);

        // case4. 다른 비밀번호 테스트 (문자열 길이 다름)
        String pwd4 = "1234";
        String enc4 = CommonUtil.encrypt(pwd4, salt);
        String neTest2 = CommonUtil.encrypt("123", salt);
        assertNotEquals(enc4, neTest2);

        // case5. 다른 salt 테스트
        String pwd5 = "1234";
        String enc5 = CommonUtil.encrypt(pwd5, salt);
        String neTest3 = CommonUtil.encrypt("123", salt);
        assertNotEquals(enc5, neTest3);

        // 파라미터 테스트
        assertThrows(IllegalArgumentException.class, () -> CommonUtil.encrypt(null, CommonUtil.getSalt()));
        assertThrows(IllegalArgumentException.class, () -> CommonUtil.encrypt("", CommonUtil.getSalt()));
        assertThrows(IllegalArgumentException.class, () -> CommonUtil.encrypt("1234", null));
        assertThrows(IllegalArgumentException.class, () -> CommonUtil.encrypt("1234", ""));
    }

    @Test
    void isNullOrEmpty() {
        assertTrue(CommonUtil.isNullOrEmpty(null));
        assertTrue(CommonUtil.isNullOrEmpty());
        assertTrue(CommonUtil.isNullOrEmpty("", "asdf", null));
        assertTrue(CommonUtil.isNullOrEmpty("", 330, 115L));
        assertTrue(CommonUtil.isNullOrEmpty(""));
        assertTrue(CommonUtil.isNullOrEmpty("aa", new ArrayList(10)));
        assertTrue(CommonUtil.isNullOrEmpty("aa", new HashMap(10)));
        assertTrue(CommonUtil.isNullOrEmpty("aa", new LinkedList<>()));
        assertFalse(CommonUtil.isNullOrEmpty(" "));
        assertFalse(CommonUtil.isNullOrEmpty("not null"));
        assertFalse(CommonUtil.isNullOrEmpty("must", "be", "not", "null"));
    }
}