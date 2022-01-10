package com.ts.trans.types;

import java.util.Arrays;

/**
 * 支付校验方式
 */
public class PayVerifyMethod {

    private String value;

    public PayVerifyMethod(String value) {
        MethodEnum method = Arrays.stream(MethodEnum.values())
                .filter(f -> f.getCode().equals(value)).findFirst().orElseThrow(() -> new IllegalArgumentException("支付验证方式有误"));
        this.value = method.getCode();
    }

    public String getValue() {
        return value;
    }

    public enum MethodEnum {
        PASSWORD("0"),
        VERIFY_CODE("1"),
        ;
        private String code;

        MethodEnum(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }
    }
}
