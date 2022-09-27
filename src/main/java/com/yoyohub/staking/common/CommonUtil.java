package com.yoyohub.staking.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    public static String getSalt() {
        Random random = new Random();
        byte[] salt = new byte[10];

        random.nextBytes(salt);

        StringBuffer sb = new StringBuffer();

        for(int i=0; i<salt.length; i++) {
            sb.append(String.format("%02x", salt[i]));
        }

        return sb.toString();
    }

    public static String encrypt(String pwd, String salt) {
        byte[] saltBytes = salt.getBytes();
        String result = "";

        byte[] temp = pwd.getBytes();
        byte[] bytes = new byte[temp.length + saltBytes.length];

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);
            byte[] b = md.digest();

            StringBuffer sb = new StringBuffer();
            for(int i=0; i<b.length; i++) {
                sb.append(Integer.toString((b[i] & 0xFF) + 256, 16).substring(1));
            }

            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {}

        return result;
    }

    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static boolean isNullOrEmpty(Object ...args) {
        if(args == null || args.length == 0) { return true; }

        for(int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if(arg == null || "".equals(arg)) {
                return true;
            }
            if(arg instanceof Map) {
                Map map = (Map) arg;
                if(map.isEmpty()) {
                    return true;
                }
            }
            if(arg instanceof List) {
                List list = (List) arg;
                if(list.isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }

}
