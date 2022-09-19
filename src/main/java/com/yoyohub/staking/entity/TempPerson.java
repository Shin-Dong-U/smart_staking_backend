package com.yoyohub.staking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TempPerson {
    @Id private String ordrIdxx;
    private String commId;
    private String phoneNo;
    private String userName;
    private String birthDay;
    private String sexCode;
    private String localCode;
    private String ci;
    private String di;
    private String resCd;
    private String resMsg;
    private LocalDateTime createdDate;
}