package com.yoyohub.staking.cert;

import com.yoyohub.staking.config.CertificationConfig;
import com.yoyohub.staking.config.CertificationConfiguration;
import com.yoyohub.staking.entity.TempPerson;
import com.yoyohub.staking.repository.TempPersonRepository;
import kr.co.kcp.CT_CLI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@Service
public class CertificationServiceImpl implements CertificationService {

    @Autowired
    private CertificationConfiguration certConfiguration;

    @Autowired
    private TempPersonRepository personRepo;

    @Override
    public CertificationResult decodeResult(HttpServletRequest request) {
        // 1. 파라미터 조회
        String resCode = request.getParameter("res_cd");
        String dnHash = request.getParameter("dn_hash");
        String ordrIdxx = request.getParameter("ordr_idxx");
        String certNo = request.getParameter("cert_no");
        String encodeCertData = request.getParameter("enc_cert_data2");

        // 2. 성공 여부 체크
        if(!isSuccess(resCode)) {
            return new CertificationResult(null, false, "인증실패");
        }

        CT_CLI cc = new CT_CLI();
        CertificationConfig certConf = certConfiguration.getCertificationConfig();

        // 3. 데이터 변조 여부 체크
        if( !validHash(cc, certConf, dnHash, ordrIdxx, certNo) ) {
            cc = null;
            return new CertificationResult(null, false, "데이터가 변조 되었습니다.");
        }

        // 4. 데이터 복호화
        Person person = decode(cc, certConf, ordrIdxx, certNo, encodeCertData);

        // 5. 객체 반환
        cc = null;

        return new CertificationResult(person, true, "");
    }

    public void saveTempPerson(CertificationResult result) {
        if(result.isSuccess()) {
            TempPerson tempPerson = convert(result.getPerson());
            personRepo.save(tempPerson);
            log.info("[본인인증성공] " + result.getPerson().getOrdrIdxx());
        }else {
            log.info("[본인인증실패]" + result.getMessage());
        }
    }

    private boolean isSuccess(String code) {
        return "0000".equals(code);
    }

    private boolean validHash(CT_CLI cc, CertificationConfig certConf, String dnHash, String ordrIdxx, String certNo) {
        String encKey = certConf.getG_conf_ENC_KEY();
        String siteCode = certConf.getG_conf_site_cd();

        return cc.checkValidHash(encKey, dnHash, siteCode + ordrIdxx + certNo);
    }

    private Person decode(CT_CLI cc, CertificationConfig certConf, String ordrIdxx, String certNo, String encodeCertData) {
        cc.decryptEncCert(certConf.getG_conf_ENC_KEY(), certConf.getG_conf_site_cd(), certNo, encodeCertData);

        Person person = new Person();
        person.setOrdrIdxx(ordrIdxx);
        person.setCertNo(certNo);
        person.setCommId(cc.getKeyValue("comm_id"));
        person.setPhoneNo(cc.getKeyValue("phone_no"));
        person.setUserName(cc.getKeyValue("user_name"));
        person.setBirthDay(cc.getKeyValue("birth_day"));
        person.setSexCode(cc.getKeyValue("sex_code"));
        person.setLocalCode(cc.getKeyValue("local_code"));
        person.setCi(cc.getKeyValue("ci"));
        person.setDi(cc.getKeyValue("di"));
        person.setCiUrl(cc.getKeyValue("ci_url"));
        person.setDiUrl(cc.getKeyValue("di_url"));
        person.setResCd(cc.getKeyValue("res_cd"));
        person.setResMsg(cc.getKeyValue("res_msg"));
        person.setTimestamp(System.currentTimeMillis());

        return person;
    }

    private TempPerson convert(Person person) {
        return new TempPerson(
                person.getOrdrIdxx(),
                person.getCommId(),
                person.getPhoneNo(),
                person.getUserName(),
                person.getBirthDay(),
                person.getSexCode(),
                person.getLocalCode(),
                person.getCi(),
                person.getDi(),
                person.getResCd(),
                person.getResMsg(),
                LocalDateTime.now()
        );
    }
}
