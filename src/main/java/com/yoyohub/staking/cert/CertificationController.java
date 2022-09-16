package com.yoyohub.staking.cert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("/certification/")
@Controller
public class CertificationController {

    /**
     * 본인인증 페이지 호출
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public String certificationApiCall(@PathVariable String orderId) {
        log.info("[본인인증 요청] " + orderId);
        // 구현
        return "cert/req";
    }

    /**
     * 본인인증 결과 수신
     * @param request
     * @return
     */
    @PostMapping("/")
    public String certificationResultCallback(HttpServletRequest request) {

        return "cert/res";
    }
}
