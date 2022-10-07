package com.yoyohub.staking.cert;

import com.yoyohub.staking.config.CertificationConfig;
import com.yoyohub.staking.config.CertificationConfiguration;
import com.yoyohub.staking.entity.TempPerson;
import com.yoyohub.staking.repository.TempPersonRepository;
import kr.co.kcp.CT_CLI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RequestMapping("/certification")
@Controller
public class CertificationController {

    @Autowired private CertificationConfiguration certConfig;
    @Autowired private CertificationService certService;
    @Autowired private TempPersonRepository tempPersonRepo;

    /**
     * 본인인증 페이지 호출
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public String certificationApiCall(@PathVariable String orderId, Model model, HttpServletRequest request) {
        log.info("[인증요청] " + orderId);

        CT_CLI cc = new CT_CLI();
        CertificationConfig config = certConfig.getCertificationConfig();

        Person person = new Person();
        person.setOrdrIdxx(orderId);
        person.setUpHash(CertificationUtil.makeUpHash(cc, config, person)); // 요청을 위한 해쉬 데이터 생성

        model.addAttribute("config", config);
        model.addAttribute("person", person);

        return "cert/req";
    }

    /**
     * 본인인증 결과 수신
     * NHN KCP에서 결과를 수신하면, 해당 결과를 복호화하여 리턴
     * @param request
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
    @PostMapping
    public String certificationResultCallback(HttpServletRequest request) {
        CertificationResult result = certService.decodeResult(request);
        certService.saveTempPerson(result);

        return "cert/res";
    }

    @ResponseBody
    @GetMapping("/result/{orderId}")
    public ResponseEntity<TempPerson> getCertificationResult(@PathVariable("orderId") String orderId) {
        TempPerson person = tempPersonRepo.findById(orderId).orElse(null);

        if(person == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT); // status : 204

        return new ResponseEntity<>(person, HttpStatus.OK); // status : 200
    }
}
