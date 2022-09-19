package com.yoyohub.staking.common;

import java.math.BigDecimal;

public class CommonUtil {
    public static String formatNumber(int number, int length) {
        return String.format("%0" + length + "d", number);
    }

    public static boolean gt(BigDecimal x, BigDecimal y) {
        return x.compareTo(y) > 0;
    }

    public static boolean gte(BigDecimal x, BigDecimal y) {
        return x.compareTo(y) >= 0;
    }

    public static boolean lt(BigDecimal x, BigDecimal y) {
        return x.compareTo(y) < 0;
    }

    public static boolean lte(BigDecimal x, BigDecimal y) {
        return x.compareTo(y) <= 0;
    }

    public static boolean gteLt(BigDecimal x, BigDecimal min, BigDecimal max) {
        return gte(x, min) && lt(x, max);
    }
}
