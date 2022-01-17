package com.ts.trans.application.impl;

import com.ts.bfs.common.exception.BFException;
import com.ts.trans.api.dto.TransferApplyRequest;
import com.ts.trans.api.dto.TransferApplyResponse;
import com.ts.trans.api.dto.TransferConfirmRequest;
import com.ts.trans.api.dto.TransferConfirmResponse;
import com.ts.trans.api.facade.TransferFacade;
import com.ts.trans.application.utils.ContextUtil;
import com.ts.trans.domain.entity.OrderInfo;
import com.ts.trans.domain.entity.PayUser;
import com.ts.trans.domain.external.MemberService;
import com.ts.trans.domain.repository.OrderInfoRepository;
import com.ts.trans.domain.service.TransferService;
import com.ts.trans.types.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 转账接口实现
 */
@Service
@Slf4j
public class TransferFacadeImpl implements TransferFacade {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TransferService transferService;
    
    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Override
    public TransferApplyResponse apply(TransferApplyRequest transferApplyRequest) {
        TransferApplyResponse response = new TransferApplyResponse();
        // 1. 获取付款方信息
        PayUser payerUser = memberService.getMeberInfo(new MemberId(ContextUtil.getData().getInternalMemberNo()));
        payerUser.setVerifyMethod(PayVerifyMethodEnum.valueOf(transferApplyRequest.getVerifyMethod()));
        payerUser.setPayPwd(new PayPwd(transferApplyRequest.getIdentityCredential()));
        // 1.2 校验支付密码
        if (payerUser.isVerifyByPwd()) {
            memberService.checkPayPwd(payerUser);
        }
        // 1.3 校验付款方权限
        memberService.checkPermission(payerUser, new ControlType(OrderTypeEnum.TRANSFER.getType()));
        // 2. 收款方校验
        PayUser payeeUser = memberService.getMeberInfo(new MemberId(transferApplyRequest.getPayeeId()));
        // 3. 触发申请转账
        try {
            OrderInfo orderInfo = transferService.transfer(payerUser, payeeUser, new Money(transferApplyRequest.getTotalAmount(), new Currency(transferApplyRequest.getCurrency())));
            // 4.订单更新
            orderInfoRepository.save(orderInfo);
            // 5.组装响应数据
            response.setOrderNo(orderInfo.getOrderId().getStrValue());
            response.setStatus(orderInfo.getOrderStatus().getCode());
            response.setTotalAmount(orderInfo.getTotalAmount().getAmout());
            response.setMessage(orderInfo.getOrderStatus().getMsg());
        } catch (Exception e) {
            log.error("转账异常：", e);
            throw new BFException("转账交易异常");
        }
        return response;
    }

    @Override
    public TransferConfirmResponse confirm(TransferConfirmRequest transferConfirmRequest) {
        return null;
    }
}
