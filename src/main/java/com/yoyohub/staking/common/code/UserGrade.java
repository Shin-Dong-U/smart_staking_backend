package com.yoyohub.staking.common.code;

import com.yoyohub.staking.common.StaticVariables;
import lombok.Getter;

import java.math.BigDecimal;
import static com.yoyohub.staking.common.CommonUtil.*;

public enum UserGrade implements EnumMapperType {

    NO_POSITION(0, "해당없음", 0, 1154400),
    SANGSA(1, "상사", 1154400, 2308800),
    TEAMJANG(2, "팀장", 2308800, 4804800),
    GOOKJANG(3, "국장", 4804800, 10010000),
    VONBUJANG(4, "본부장", 10010000, 20800000),
    SANGMU(5, "상무", 20800000, 41600000),
    CHONGJANG(6, "총장", 41600000, 83200000),
    CROWN(7, "크라운", 83200000, 223600000),
    ROYAL_CROWN(8, "로얄크라운", 223600000, Integer.MAX_VALUE),
    JISAJANG(9, "지사장", 0, Integer.MAX_VALUE);

    @Getter private int code;
    @Getter private String desc;
    @Getter private int gteCriteria;
    @Getter private int ltCriteria;

    UserGrade(int code, String desc, int gteCriteria, int ltCriteria) {
        this.code = code;
        this.desc = desc;
        this.gteCriteria = gteCriteria;
        this.ltCriteria = ltCriteria;
    }

    /**
     * 현재 적용되는 등급 반환
     *
     * 1. 현재 등급이 NO_POSITION 이라면 보유코인 시장가 + 추가 입금액으로 등급 산정
     * 2. 현재 등급이 NO_POSITION 이 아니라면 추가 입금액과 무관하게 보유코인 시장가로만 등급 산정
     *
     * @param coin 스테이킹 계좌에 보유중인 Y코인
     * @param deposit 추가 입금액
     * @return
     */
    public static UserGrade findByCoinAndDeposit(BigDecimal coin, int deposit) {
        BigDecimal marketPrice = coin.multiply(new BigDecimal(StaticVariables.YCOIN_PRICE));

        // NO_POSITION 등급은 보유코인 시장가 + 입금액으로 등급 산정
        if(isNoPosition(coin))
            marketPrice = marketPrice.add(new BigDecimal(deposit));

        for(UserGrade userGrade : UserGrade.values()) {
            if( gte(marketPrice, new BigDecimal(userGrade.gteCriteria)) &&
                lt(marketPrice, new BigDecimal(userGrade.ltCriteria))) {
                return userGrade;
            }
        }

        return NO_POSITION;
    }

    private static boolean isNoPosition(BigDecimal coin) {
        final BigDecimal MIN_COIN_CRITERIA = new BigDecimal(888);

        return coin.compareTo(MIN_COIN_CRITERIA) < 0;
    }
}


