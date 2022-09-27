package com.yoyohub.staking.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    private String id;

    @Setter private String password;

    @Setter private String password2;

    private String recommendId;

    private int role;

    @Getter private String name;

    private String phone;

    private String email;

    private String yolifeId;

    private String ci;

    private String di;

    private int sexCode;

    private int localCode;

    private String birth;

    private String zip;

    private String addr1;

    private String addr2;

    private int bankCode;

    private String bankAccount;

    private String bankAccountName;

    private String idCardFilePath;

    private String bankbookFilePath;

    @Setter private LocalDateTime registeredDate;

    @Setter private LocalDateTime modifiedDate;

    @Builder
    public User(String id, String password, String password2, String recommendId, int role, String name, String phone, String email, String yolifeId, String ci, String di, int sexCode, int localCode, String birth, String zip, String addr1, String addr2, int bankCode, String bankAccount, String bankAccountName, String idCardFilePath, String bankbookFilePath, LocalDateTime registeredDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.password = password;
        this.password2 = password2;
        this.recommendId = recommendId;
        this.role = role;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.yolifeId = yolifeId;
        this.ci = ci;
        this.di = di;
        this.sexCode = sexCode;
        this.localCode = localCode;
        this.birth = birth;
        this.zip = zip;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.bankCode = bankCode;
        this.bankAccount = bankAccount;
        this.bankAccountName = bankAccountName;
        this.idCardFilePath = idCardFilePath;
        this.bankbookFilePath = bankbookFilePath;
        this.registeredDate = registeredDate;
        this.modifiedDate = modifiedDate;
    }
}
