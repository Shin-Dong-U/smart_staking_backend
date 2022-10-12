package com.yoyohub.staking.common.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CommonCodeController {

    @Autowired private CommonCode commonCode;

    @GetMapping("/common/code")
    public ResponseEntity<Map<String, List<EnumMapperValue>>> getEnumMapper() {
        return new ResponseEntity<>(commonCode.enumMapper().getCodes(), HttpStatus.OK);
    }
}
