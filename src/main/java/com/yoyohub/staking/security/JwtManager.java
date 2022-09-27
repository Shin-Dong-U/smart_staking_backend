package com.yoyohub.staking.security;

import com.yoyohub.staking.common.code.enums.Role;
import com.yoyohub.staking.common.code.enums.TokenType;
import com.yoyohub.staking.entity.RefreshToken;
import com.yoyohub.staking.entity.User;
import com.yoyohub.staking.repository.RefreshTokenRepository;
import com.yoyohub.staking.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.yoyohub.staking.common.CommonUtil.isNullOrEmpty;
import static com.yoyohub.staking.common.CommonUtil.removeCookie;
import static com.yoyohub.staking.common.code.enums.TokenType.*;

@Slf4j
@PropertySource("classpath:${spring.profiles.active}/token.properties")
@Component
public class JwtManager {

    @Value("${jwt-access-token-security-key}") private String accessSecurityKey;
    @Value("${jwt-refresh-token-security-key}") private String refreshSecurityKey;

    @Autowired private RefreshTokenRepository refreshTokenRepo;
    @Autowired private UserRepository userRepo;

    /**
     * 회원 정보를 담은 JWT 토큰을 생성한다.
     *
     * @param user User 정보를 담은 객체
     * @return String JWT 토큰
     */
    private String generateJwtToken(User user, String securityKey, long expiredTime) {
        String id = user.getId();
        Date now = new Date();

        return Jwts.builder()
                .setSubject(id)
                .setHeader(createHeader())
                .setClaims(createClaims(user)) // 클레임, 토큰에 포함될 정보
                .setExpiration(new Date(now.getTime() + expiredTime)) // 만료일
                .signWith(SignatureAlgorithm.HS256, securityKey)
                .compact();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256"); // 해시 256 암호화
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    /**
     * 클레임(Claim)을 생성한다.
     *
     * @param user 토큰을 생성하기 위한 계정 정보를 담은 객체
     * @return Map<String, Object>
     *     getId() -> return String userId
     *     getRole() -> return String userRole
     */
    private Map<String, Object> createClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", Role.find(user.getRole()).getDesc());

        return claims;
    }

    /**
     * JWT 토큰을 식별한다.
     *
     * @param token JWT 토큰
     * @param type JWT 토큰 타입 (ACCESS, REFRESH)
     * @return boolean 토큰 유효 여부
     */
    public boolean isValid(String token, TokenType type) {
        try {
            Claims claims = getClaims(token, type);
            String id = claims.get("id", String.class);
            String role = claims.get("role", String.class);
            Date expiration = claims.getExpiration();

            // 토큰 데이터 존재 여부 확인
            if(isNullOrEmpty(id, role, expiration)) { return false; }

            return true;
        } catch (ExpiredJwtException exception) { // 토큰이 만료되었을 경우
            return false;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException exception) { // 토큰이 변조되었을 경우
            log.error("[토큰변조] : ", exception.getMessage());
            return false;
        } catch (NullPointerException exception) { // 토큰이 없는경우
            return false;
        }
    }

    private Claims getClaims(String token, TokenType type) {
        String securityKey = type == TokenType.ACCESS_TOKEN ? accessSecurityKey : refreshSecurityKey;

        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(securityKey))
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isExpired(String token, TokenType type) {
        Date expiration = null;
        try {
            Claims claims = getClaims(token, type);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            return true;
        }

        return isExpired(expiration);
    }

    public boolean isExpired(Date expiration) {
        return new Date().after(expiration);
    }

    /**
     * JWT 토큰을 해독하여 회원 ID 반환
     *
     * @param token JWT 토큰
     * @param type JWT 토큰 타입 (ACCESS, REFRESH)
     * @return 회원 아이디
     */
    public String getId(String token, TokenType type) {
        return getClaims(token, type).getOrDefault("id", "").toString();
    }

    /**
     * Access Token, Refresh Token을 생성한다.
     * 1. Access Token, Refresh Token 후 쿠키 생성
     * 2. Refresh Token DB 저장
     *
     * @param response
     * @param user 회원 정보
     */
    public void issueToken(HttpServletResponse response, User user) {
        issueAccessToken(response, user);
        issueRefreshToken(response, user);
    }

    public void issueAccessToken(HttpServletResponse response, User user) {
        String token = generateJwtToken(user, accessSecurityKey, ACCESS_TOKEN.getExpiredMillisecond());

        Cookie cookie = new Cookie(TokenType.ACCESS_TOKEN.getDesc(), token);
        cookie.setMaxAge(ACCESS_TOKEN.getExpiredSecond());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void issueRefreshToken(HttpServletResponse response, User user) {
        String token = generateJwtToken(user, refreshSecurityKey, REFRESH_TOKEN.getExpiredMillisecond());

        Cookie cookie = new Cookie(REFRESH_TOKEN.getDesc(), token);
        cookie.setMaxAge(REFRESH_TOKEN.getExpiredSecond());
        cookie.setPath("/");
        response.addCookie(cookie);

        saveRefreshToken(user, token);
    }

    /**
     * Refresh Token이 유효하다면 Access Token을 재발급한다.
     *
     * @param response
     * @param refreshToken
     * @return boolean 토큰 재발급 성공 여부
     */
    public boolean reissueAccessToken(HttpServletResponse response, String refreshToken) {
        String userId = getId(refreshToken, TokenType.REFRESH_TOKEN);

        // Refresh Token 검증 - DB 조회
        RefreshToken token = refreshTokenRepo.findByUserIdAndToken(userId, refreshToken);
        if(token == null) { return false; }

        // 토큰 생성을 위해 회원 정보 조회
        User user = userRepo.findById(userId).orElse(null);
        if(user == null) { return false; }

        issueAccessToken(response, user);

        return true;
    }

    /**
     * Refresh Token을 DB에 저장한다.
     *
     * @param user 회원 정보
     * @param token Refresh Token
     */
    public void saveRefreshToken (User user, String token) {
        RefreshToken refreshToken = RefreshToken
                .builder()
                .userId(user.getId())
                .token(token)
                .build();
        refreshTokenRepo.save(refreshToken);
    }

    public void removeAllTokenCookies(HttpServletResponse response) {
        removeCookie(response, "access_token");
        removeCookie(response, "refresh_token");
    }

    /**
     * 쿠키에서 Access Token, Refresh Token 획득.
     *
     * @param cookies
     * @return [0] : Access Token, [1] : Refresh Token
     */
    public String[] getTokensFromCookies(Cookie[] cookies) {
        if(cookies == null) { return new String[]{null, null}; }

        String[] tokens = new String[2];

        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(TokenType.ACCESS_TOKEN.getDesc())) {
                tokens[0] = cookie.getValue();
            }
            if(cookie.getName().equals(TokenType.REFRESH_TOKEN.getDesc())) {
                tokens[1] = cookie.getValue();
            }
        }

        return tokens;
    }
}