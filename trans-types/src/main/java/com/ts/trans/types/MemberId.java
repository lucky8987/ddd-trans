package com.ts.trans.types;

/**
 * 会员编号
 */
public class MemberId {

    private Long value;

    public MemberId(Long value) {
        if (value == null) {
            throw new IllegalArgumentException("会员编号不能为空");
        }
        this.value = value;
    }

    public MemberId(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("会员编号不能为空");
        }
        try {
            this.value = Long.valueOf(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("会员编号只能是数字", e);
        }
    }

    public Long getValue() {
        return value;
    }

    public String getStrValue() {
        return String.valueOf(value);
    }
}
