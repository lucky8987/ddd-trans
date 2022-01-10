package com.ts.trans.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @program: pay-trans
 * @description: 转账申请响应类
 * @author: zhaochen
 * @create: 2020-11-11 20:31
 **/
@Data
public class TransferApplyResponse implements Serializable {

    private String orderNo;

    private String outOrderNo;

    private BigDecimal totalAmount;

    private String status;

    private String message;

    private String bankTradeNo;
}
