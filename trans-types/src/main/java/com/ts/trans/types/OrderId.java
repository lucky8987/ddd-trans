package com.ts.trans.types;

/**
 * 订单编号
 */
public class OrderId {

    private Long value;

    public OrderId(Long value) {
        if (value == null) {
            throw new IllegalArgumentException("订单号不能为空");
        }
        this.value = value;
    }

    public OrderId(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("订单号不能为空");
        }
        try {
            this.value = Long.valueOf(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("订单号只能是数字",e);
        }
    }

    public Long getValue() {
        return value;
    }

    public String getStrValue() {
        return String.valueOf(value);
    }
}
