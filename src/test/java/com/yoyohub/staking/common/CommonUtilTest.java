package com.yoyohub.staking.common;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

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
}