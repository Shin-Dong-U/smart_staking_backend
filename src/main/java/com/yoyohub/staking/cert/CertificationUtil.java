package com.yoyohub.staking.cert;

import com.yoyohub.staking.config.CertificationConfig;
import kr.co.kcp.CT_CLI;

import java.util.HashMap;
import java.util.Map;

public class CertificationUtil {
    private static Map<String, Person> tempPeople = new HashMap<>();

    public static String makeUpHash(CT_CLI cc, CertificationConfig config, Person person) {
       String upHash = "";
       if( "Y".equals(config.getCert_able_yn()) )
       {
           upHash = cc.makeHashData( config.getG_conf_ENC_KEY(), config.getG_conf_site_cd()   +
                   person.getOrdrIdxx() +
                   ( "Y".equals(config.getWeb_siteid_hashYN())? config.getG_conf_web_siteid():"" ) +
                   ""   +
                   "00" +
                   "00" +
                   "00" +
                   ""   +
                   ""
           );
       } else {
           upHash = cc.makeHashData( config.getG_conf_ENC_KEY(), config.getG_conf_site_cd()   +
                   person.getOrdrIdxx() +
                   ( "Y".equals(config.getWeb_siteid_hashYN())? config.getG_conf_web_siteid():"" ) +
                   convertNullToEmptyString(person.getUserName()) +
                   numberStringZerofill(person.getYear())      +
                   numberStringZerofill(person.getMonth())     +
                   numberStringZerofill(person.getDay())       +
                   convertNullToEmptyString(person.getSexCode())  +
                   convertNullToEmptyString(person.getLocalCode())
           );
       }

       return upHash;
   }

    public static String convertNullToEmptyString( String val )
    {
        if ( val == null ) val = "";
        return  val;
    }

    //!!중요 해당 함수는 year, month, day 변수가 null 일 경우 00 으로 치환합니다
    public static String numberStringZerofill( String val )
    {
        String ret_val = "";

        if ( val == null      ) val = "00";
        if ( val.equals( "" ) ) val = "00";

        ret_val = val.length() == 1? ("0" + val) : val;

        return  ret_val;
    }
}
