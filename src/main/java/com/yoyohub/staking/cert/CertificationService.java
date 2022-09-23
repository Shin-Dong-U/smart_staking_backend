package com.yoyohub.staking.cert;

import javax.servlet.http.HttpServletRequest;

public interface CertificationService {
    public CertificationResult decodeResult(HttpServletRequest request);
    public void saveTempPerson(CertificationResult result);
}
