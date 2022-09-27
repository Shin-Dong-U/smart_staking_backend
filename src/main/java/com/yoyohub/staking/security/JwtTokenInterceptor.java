package com.yoyohub.staking.security;

import com.yoyohub.staking.common.code.enums.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired private JwtManager jwtManager;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        String[] tokens = jwtManager.getTokensFromCookies(cookies);
        String accessToken = tokens[0];
        String refreshToken = tokens[1];

        // 1. refreshToken 검증
        boolean isValidRefreshToken = jwtManager.isValid(refreshToken, TokenType.REFRESH_TOKEN);
        if (!isValidRefreshToken) {
            response.sendError(401, "로그인이 필요합니다.");
            return false;
        }

        // 2. accessToken 검증
        boolean isValidAccessToken = jwtManager.isValid(accessToken, TokenType.ACCESS_TOKEN);
        boolean isExpired = jwtManager.isExpired(accessToken, TokenType.ACCESS_TOKEN);
        if (isValidAccessToken && !isExpired) {
            return true;
        }

        // 3. accessToken 만료시 refreshToken 유효기간 검증
        isExpired = jwtManager.isExpired(refreshToken, TokenType.REFRESH_TOKEN);
        if (isExpired) {
            response.sendError(401, "로그인이 필요합니다.");
            return false;
        }

        // 4. refreshToken이 유효하다면 accessToken 재발급
        boolean isSuccess = jwtManager.reissueAccessToken(response, refreshToken);
        if (!isSuccess) { // 재발급 실패
            response.sendError(401, "로그인이 필요합니다.");
            return false;
        }

        return true;
    }
}
