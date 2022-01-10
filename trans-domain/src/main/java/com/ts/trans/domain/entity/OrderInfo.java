package com.ts.trans.domain.entity;

import com.ts.trans.types.*;
import java.util.Date;
import lombok.Data;

/**
 * 订单主体（值对象）
 */
@Data
public class OrderInfo {

    /**
     * 订单编号
     */
    private OrderId orderId;

    /**
     * 付款方
     */
    private PayUser payer;

    /**
     * 收款方
     */
    private PayUser payee;

    /**
     * 订单金额
     */
    private Money totalAmount;

    /**
     * 交易金额
     */
    private Money payAmount;

    /**
     * 手续费
     */
    private Money feeAmount;

    /**
     * 订单类型
     */
    private OrderTypeEnum orderType;

    /**
     * 订单状态
     */
    private OrderStatusEnum orderStatus;

    /**
     * 机构编号
     */
    private Org org;

    private Channel channel;

    private Date createTime;

    private Date updateTime;

    private String successDate;

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        if (OrderStatusEnum.SUCCESS == orderStatus) {
            this.setSuccessDate(new Date().toLocaleString());
        }
        this.orderStatus = orderStatus;
    }

}
