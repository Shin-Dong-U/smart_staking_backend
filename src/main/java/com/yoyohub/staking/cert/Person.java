package com.yoyohub.staking.cert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String ordrIdxx;
    private String certNo;
    private String commId;
    private String phoneNo;
    private String userName;
    private String birthDay;
    private String year;
    private String month;
    private String day;
    private String sexCode;
    private String localCode;
    private String ci;
    private String di;
    private String ciUrl;
    private String diUrl;
    private String resCd;
    private String resMsg;
    private String upHash;
    private String dnHash;
    private long timestamp;
}