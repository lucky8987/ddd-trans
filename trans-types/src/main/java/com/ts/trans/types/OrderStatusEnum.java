package com.ts.trans.types;

/**
 * 订单状态枚举
 */
public enum OrderStatusEnum {
    SUCCESS("S", "支付成功"),
    PROCESSING("P", "处理中"),
    PART_REFUND("PR", "部分退款"),
    FULL_REFUND("FR", "全额退款"),
    REFUNDING("R", "退款中"),

    FAIL("F", "支付失败"),
    ORDER_CLOSURE("C", "订单关闭"),
    INIT("I", "待支付"),
    RE_EXCHANGE("RE","退汇"),

    //该状态仅用在批量代付中
    PAY_PROCESSING("U", "发起支付"),
    ;

    private String code;
    private String msg;

    OrderStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static OrderStatusEnum valueOfCode(String status) {
        for (OrderStatusEnum value : values()) {
            if (value.getCode().equals(status)) {
                return value;
            }
        }
        return null;
    }

    public static String getMsgByCode(String status){
        for(OrderStatusEnum value:values()){
            if(value.getCode().equals(status)){
                return value.getMsg();
            }
        }
        return "-";
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
