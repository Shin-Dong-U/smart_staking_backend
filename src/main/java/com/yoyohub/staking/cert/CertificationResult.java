package com.yoyohub.staking.cert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationResult {
    private Person person;
    private boolean isSuccess;
    private String message;
}
