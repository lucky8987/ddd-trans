package com.ts.trans.api.facade;


import com.ts.trans.api.dto.TransferApplyRequest;
import com.ts.trans.api.dto.TransferApplyResponse;
import com.ts.trans.api.dto.TransferConfirmRequest;
import com.ts.trans.api.dto.TransferConfirmResponse;

/**
 * @program: pay-trans
 * @description: 转账服务
 * @author: zhaochen
 * @create: 2020-05-25 16:37
 **/
public interface TransferFacade {

    /**
     * 转账申请接口，使用密码验证
     * @param transferApplyRequest
     * @return
     */
    TransferApplyResponse apply(TransferApplyRequest transferApplyRequest);


    /**
     * 转账确认接口，使用验证码验证
     * @param transferConfirmRequest
     * @return
     */
    TransferConfirmResponse confirm(TransferConfirmRequest transferConfirmRequest);



}
