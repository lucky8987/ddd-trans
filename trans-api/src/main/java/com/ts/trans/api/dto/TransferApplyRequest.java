package com.ts.trans.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @program: pay-trans
 * @description: 转账申请请求类
 * @author: zhaochen
 * @create: 2020-11-11 20:30
 **/
@Data
public class TransferApplyRequest implements Serializable {

    private String serviceType;

    /**
     * 付款方ID
     */
    private String payerId;

    /**
     * 收款方ID
     */
    private String payeeId;

    /**
     * 收款方户名
     */
    private String payeeAcctName;

    /**
     * 收款方联行号
     */
    private String payeeBankNo;

    /**
     * 金额
     */
    private BigDecimal totalAmount;

    /**
     * 转账类型
     */
    private String transferType;

    /**
     * 订单号
     */
    private String outOrderNo;

    /**
     * 币种
     */
    private String currency;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 异步通知地址
     */
    private String notifyUrl;

    /**
     * 外部订单时间
     */
    private String outOrderTime;

    private String identityCredential;

    /**
     * 验证方式 0 密码 1 验证码
     * <p>
     * 该参数决定发送到渠道的参数值，决定是否发送验证码
     */
    private String verifyMethod;

    /**
     * 附言
     */
    private String summary;

}
