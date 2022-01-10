package com.ts.trans.types;

/**
 * 订单编号
 */
public class OrderId {

    private Long value;

    public OrderId(Long value) {
        this.value = value;
    }

    public OrderId(String value) {
        this.value = Long.valueOf(value);
    }

    public Long getValue() {
        return value;
    }

    public String getStrValue() {
        return String.valueOf(value);
    }
}
