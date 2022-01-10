package com.ts.trans.external;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.ts.pay.channel.api.facade.PayFacade;
import com.ts.pay.channel.common.enums.AccountPayTypeEnum;
import com.ts.pay.channel.common.enums.NeedEnum;
import com.ts.pay.channel.common.enums.TradeStatusEnum;
import com.ts.pay.channel.common.request.pay.AgentPayRequest;
import com.ts.pay.channel.common.response.pay.AgentPayResponse;
import com.ts.trans.domain.entity.PayUser;
import com.ts.trans.domain.external.ChannelService;
import com.ts.trans.domain.external.CommonService;
import com.ts.trans.types.Money;
import com.ts.trans.types.OrderStatusEnum;
import com.ts.trans.types.OrderTypeEnum;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 渠道服务接口
 */
@Component
@Slf4j
public class ChannelServiceImpl implements ChannelService {

    @Reference
    private PayFacade payFacade;

    @Autowired
    private CommonService commonService;

    /**
     * 账户支付
     * @param payer
     * @param payee
     * @param money
     * @param orderType
     * @return
     */
    @Override
    public OrderStatusEnum agentPay(PayUser payer, PayUser payee, Money money, OrderTypeEnum orderType) {
        OrderStatusEnum statusEnum;
        AgentPayRequest agentPayRequest = new AgentPayRequest();
        agentPayRequest.setOrderNo(String.valueOf(commonService.getId()));
        agentPayRequest.setOrderDate(DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT));
        if (payer.isVerifyByCode()) {
            // 发送验证码
            agentPayRequest.setVerifySms(NeedEnum.Y.getCode());
        }
        agentPayRequest.setMerchantId(payer.getName());
        //付款方商户名称
        agentPayRequest.setMerchantName(payer.getName());
        //要发短信则需要用余额支付类型
        agentPayRequest.setPayType(AccountPayTypeEnum.BALANCE_PAY.getCode());
        agentPayRequest.setPayerAcctNo(payer.getId().getStrValue());
        agentPayRequest.setPayerAcctName(payer.getName());
        agentPayRequest.setPayeeAcctNo(payee.getId().getStrValue());
        agentPayRequest.setPayeeAcctName(payee.getName());
        agentPayRequest.setCcy(money.getCurrency().getValue());
        agentPayRequest.setAmount(money.getAmout());
        agentPayRequest.setSystemNo("10114");
        // 发起通道调用
        try {
            AgentPayResponse agentPayResponse = payFacade.agentPay(agentPayRequest);
            if (TradeStatusEnum.SUCCESS.getCode().equals(agentPayResponse.getStatus())) {
                // 明确成功
                statusEnum = OrderStatusEnum.SUCCESS;
            } else if (TradeStatusEnum.FAIL.getCode().equals(agentPayResponse.getStatus())) {
                // 明确失败
                statusEnum = OrderStatusEnum.FAIL;
            } else {
                // 其余状态：处理中
                statusEnum = OrderStatusEnum.PROCESSING;
            }

        } catch (Exception e) {
            log.error("渠道异常：", e);
            // 异常：订单当做处理中，通过查询确定终态
            statusEnum = OrderStatusEnum.PROCESSING;
        }
        return statusEnum;
    }
}
