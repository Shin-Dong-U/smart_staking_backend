package com.yoyohub.staking.config;

import kr.co.kcp.CT_CLI;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@PropertySource("classpath:${spring.profiles.active}/certification.properties")
@Configuration
public class CertificationConfiguration {

    @Autowired
    private Environment env;

    private CertificationConfig certConfig;

    @PostConstruct
    public CertificationConfig getCertificationConfig() {
        if(certConfig == null) {
            CT_CLI cc = new CT_CLI();
            certConfig = new CertificationConfig(
                env.getProperty("g_conf_site_cd"),
                env.getProperty("g_conf_web_siteid"),
                env.getProperty("g_conf_ENC_KEY"),
                env.getProperty("g_conf_gw_url"),
                env.getProperty("g_conf_Ret_URL"),
                cc.getKCPLibVer(),
                env.getProperty("web_siteid_hashYN"),
                env.getProperty("cert_able_yn")
            );
        }

        return certConfig;
    }
}