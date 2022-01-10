package com.ts.trans.repository.mapper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 交易订单表
 * </p>
 *
 * @author Auto Generate
 * @since 2020-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order_info")
public class OrderInfoDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 分布式唯一自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 收款方会员号
     */
    private String payeeId;

    /**
     * 收款方名称
     */
    private String payeeName;

    /**
     * 真实收款方ID
     */
    private String realPayeeId;

    /**
     * 真实收款方名称
     */
    private String realPayeeName;

    /**
     * 收款方账户号
     */
    private String payeeAcctNo;

    /**
     * 收款方账户名
     */
    private String payeeAcctName;

    /**
     * 付款方会员号
     */
    private String payerId;

    /**
     * 付款方名称
     */
    private String payerName;

    /**
     * 真实收款方ID
     */
    private String realPayerId;

    /**
     * 真实收款方名称
     */
    private String realPayerName;

    /**
     * 付款方账户号
     */
    private String payerAcctNo;

    /**
     * 付款方账户名
     */
    private String payerAcctName;

    /**
     * 操作人ID
     */
    private String operatorId;

    /**
     * 操作人名称
     */
    private String operatorName;

    /**
     * 所属区域
     */
    private String region;

    /**
     * 商户订单号
     */
    private String outOrderNo;

    /**
     * 内部订单号
     */
    private String orderNo;

    /**
     * 渠道订单号
     */
    private String channelOrderNo;

    /**
     * 订单金额
     */
    private BigDecimal totalAmount;

    /**
     * 交易金额
     */
    private BigDecimal payAmount;

    /**
     * 手续费
     */
    private BigDecimal feeAmount;

    /**
     * 手续费费率
     */
    private BigDecimal feeRate;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 开户渠道
     */
    private String channelCode;

    /**
     * 请求来源
     */
    private String source;

    /**
     * 订单过期时间
     */
    private Date orderExpTime;

    /**
     * 币种 CNY-人民币
     */
    private String currency;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 订单成功时间
     */
    private String successDate;

    /**
     * 已退款金额
     */
    private BigDecimal refundedAmount;

    /**
     * 异步通知地址
     */
    private String notifyUrl;

    /**
     * 前台通知地址
     */
    private String returnUrl;

    /**
     * 商户订单时间
     */
    private Date outOrderTime;

    /**
     * 订单状态描述
     */
    private String description;

    /**
     * imei码
     */
    private String imei;

    /**
     * 机构号
     */
    private String orgCode;

    /**
     * 子机构号
     */
    private String subOrgCode;

    /**
     * 订单成功时间
     */
    private Date successTime;

    /**
     * 逻辑删除标志,0正常，1为删除
     */
    private String deleteFlag;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 乐观并发控制
     */
    private Integer version;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单更新时间
     */
    private Date updateTime;


    /** 银行交易流水号 */
    private String bankTradeNo;

    /**
     * 结算状态
     */
    private String settleStatus;

    /**
     * 担保费
     */
    private BigDecimal grtAmount;

    /**
     * 担保费率值
     */
    private BigDecimal grtRate;

    /**
     * 渠道返回信息
     */
    private String channelResponse;

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
     * 来源机构
     */
    private String sourceOrganization;

    /**
     * 商户标签，只有运营后台展示
     */
    private String merchantTag;

}
