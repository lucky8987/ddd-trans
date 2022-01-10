    package com.ts.trans.types;

/**
 * @author zhaochen
 */
public enum OrderTypeEnum {

    RECHARGE("1", "充值订单"),
    WITHDRAW("2", "提现订单"),
    TRANSFER("3", "转账订单"),
    PAYMENT("4", "支付订单"),
    REPAY("5","代付订单"),
    ;

    private String type;

    private String desc;


    OrderTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static OrderTypeEnum getByType(String type) {
        for (OrderTypeEnum value : values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }
}
