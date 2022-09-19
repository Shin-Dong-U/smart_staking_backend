package com.yoyohub.staking.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CertificationConfig {
    private String g_conf_site_cd;
    private String g_conf_web_siteid;
    private String g_conf_ENC_KEY;
    private String g_conf_gw_url;
    private String g_conf_Ret_URL;
    private String kcp_lib_ver;
    private String web_siteid_hashYN;
    private String cert_able_yn;
}
