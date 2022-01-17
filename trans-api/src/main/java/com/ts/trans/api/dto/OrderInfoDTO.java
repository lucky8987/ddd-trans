package com.ts.trans.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: pay-trans
 * @description: 交易订单Dto
 * @author: zhaochen
 * @create: 2020-04-07 10:53
 **/
@Data
@Accessors(chain = true)
public class OrderInfoDTO implements Serializable {

    /**
     *分布式唯一自增id
     */
    private Long id;
    /**
     *收款方会员号
     */
    private String payeeId;
    /**
     *收款方名称
     */
    private String payeeName;
    /**
     * 收款方账户号
     */
    private String payeeAcctNo;

    /**
     * 收款方账户名
     */
    private String payeeAcctName;

    /**
     * 付款方账户号
     */
    private String payerAcctNo;

    /**
     * 付款方账户名
     */
    private String payerAcctName;
    /**
     *付款方会员号
     */
    private String payerId;
    /**
     *付款方名称
     */
    private String payerName;
    /**
     *操作人ID
     */
    private String operatorId;
    /**
     *操作人名称
     */
    private String operatorName;

    /**
     *所属区域
     */
    private String region;
    /**
     *商户订单号
     */
    private String outOrderNo;
    /**
     *内部订单号
     */
    private String orderNo;
    /**
     *渠道订单号
     */
    private String channelOrderNo;
    /**
     *订单金额
     */
    private BigDecimal totalAmount;
    /**
     *交易金额
     */
    private BigDecimal payAmount;
    /**
     *手续费
     */
    private BigDecimal feeAmount;
    /**
     * 担保费
     */
    private BigDecimal grtAmount;
    /**
     *订单类型
     */
    private String orderType;
    /**
     *支付类型
     */
    private String payType;

    /**
     *支付类型,用于前端展示
     */
    private String payTypeShow;
    /**
     *订单来源
     */
    private String source;

    /**
     * 订单过期时间
     */
    private Date orderExpTime;
    /**
     *币种 CNY-人民币
     */
    private String currency;
    /**
     *订单状态
     */
    private String orderStatus;
    /**
     *订单成功时间
     */
    private String successDate;
    /**
     *已退款金额
     */
    private BigDecimal refundedAmount;
    /**
     *异步通知地址
     */
    private String notifyUrl;
    /**
     *前台通知地址
     */
    private String returnUrl;
    /**
     *商户订单时间
     */
    private Date outOrderTime;

    /**
     *订单状态描述
     */
    private String description;
    /**
     *机构号
     */
    private String orgCode;
    /**
     * 子机构号
     */
    private String subOrgCode;
    /**
     *订单成功时间
     */
    private Date successTime;
    /**
     *备注：传vk信息
     */
    private String remarks;

    /**
     * 备注
     */
    private String remark;

    private String imei;

    /**
     *订单创建时间
     */
    private Date createTime;
    /**
     *订单更新时间
     */
    private Date updateTime;

    /**
     * 无其他作用，数据库无此字段,仅用来标识是消费还是收款
     */
    private String orderSubType;

    private String bankTradeNo;

    private String channelCode;

    /**
     * 门店code
     */
    private String storeCode;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 渠道商名称
     */
    private String channelBusinessName;

    /**
     * 渠道商id
     */
    private String channelBusinessId;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 商户标签，只有运营后台展示
     */
    private String merchantTag;

    /**
     * 结算状态
     */
    private String settleStatus;

    /**
     * 来源机构
     */
    private String sourceOrganization;

    private String summary;

}
