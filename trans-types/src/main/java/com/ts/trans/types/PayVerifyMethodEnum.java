package com.ts.trans.types;

import java.util.Arrays;

public enum PayVerifyMethodEnum {

    PASSWORD("0"),
    VERIFY_CODE("1"),
    ;
    private String code;

    PayVerifyMethodEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static PayVerifyMethodEnum getByCode(String code) {
        return Arrays.stream(values()).filter(f -> f.getCode().equals(code)).findFirst().orElse(null);
    }
}
