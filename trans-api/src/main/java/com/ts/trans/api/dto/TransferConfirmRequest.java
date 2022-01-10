package com.ts.trans.api.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @program: pay-trans
 * @description: 转账申请请求类
 * @author: zhaochen
 * @create: 2020-11-11 20:30
 **/
@Data
public class TransferConfirmRequest implements Serializable {

    /**
     * 订单号
     */
    private String outOrderNo;

    private String identityCredential;

}
