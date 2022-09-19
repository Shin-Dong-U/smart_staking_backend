package com.yoyohub.staking.common.code;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnumMapper {

    private Map<String, List<EnumMapperValue>> codes = new LinkedHashMap<>();

    public void put(String key, Class<? extends EnumMapperType> e) {
        List<EnumMapperValue> enumValues = toEnumValues(e);
        codes.put(key, enumValues);
    }

    private List<EnumMapperValue> toEnumValues(Class<? extends EnumMapperType> e){
        return Arrays.stream(e.getEnumConstants())
                .map(EnumMapperValue::new)
                .collect(Collectors.toList());
    }

    public Map<String, List<EnumMapperValue>> getCodes() {
        return codes;
    }
}
