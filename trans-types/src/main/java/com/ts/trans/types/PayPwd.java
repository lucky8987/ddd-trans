package com.ts.trans.types;

/**
 * 支付密码
 */
public class PayPwd {

    private String value;

    public PayPwd(String value){
        if (value == null || "".equals(value)) {
            throw new IllegalArgumentException("支付密码不能为空");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
