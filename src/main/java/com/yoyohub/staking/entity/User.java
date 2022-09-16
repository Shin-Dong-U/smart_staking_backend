package com.yoyohub.staking.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    private String password;

    private String password2;

    private String recommendId;

    private String adminYn;

    private String managerYn;

    private String name;

    private String phone;

    private String email;

    private String ciCode;

    private String diCode;

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

    private LocalDateTime registeredDate;

    private LocalDateTime modifiedDate;

    @Builder
    public User(String id, String password, String password2, String recommendId, String adminYn, String managerYn, String name, String phone, String email, String ciCode, String diCode, int sexCode, int localCode, String birth, String zip, String addr1, String addr2, int bankCode, String bankAccount, String bankAccountName, String idCardFilePath, String bankbookFilePath, LocalDateTime registeredDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.password = password;
        this.password2 = password2;
        this.recommendId = recommendId;
        this.adminYn = adminYn;
        this.managerYn = managerYn;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.ciCode = ciCode;
        this.diCode = diCode;
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
