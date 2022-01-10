package com.ts.trans.types;

/**
 * 会员编号
 */
public class MemberId {

    private Long value;

    public MemberId(Long value) {
        this.value = value;
    }

    public MemberId(String value) {
        this.value = Long.valueOf(value);
    }

    public Long getValue() {
        return value;
    }

    public String getStrValue() {
        return String.valueOf(value);
    }
}
