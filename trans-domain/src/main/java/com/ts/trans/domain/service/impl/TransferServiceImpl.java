package com.ts.trans.domain.service.impl;

import com.ts.trans.domain.entity.OrderInfo;
import com.ts.trans.domain.entity.PayUser;
import com.ts.trans.domain.external.ChannelService;
import com.ts.trans.domain.external.CommonService;
import com.ts.trans.domain.external.MemberService;
import com.ts.trans.domain.repository.OrderInfoRepository;
import com.ts.trans.domain.service.TransferService;
import com.ts.trans.types.Money;
import com.ts.trans.types.OrderId;
import com.ts.trans.types.OrderStatusEnum;
import com.ts.trans.types.OrderTypeEnum;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransferServiceImpl implements TransferService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    /**
     * 发起转账
     * @param sourceUser  转出方
     * @param targetAccount 转入方
     * @param money 金额
     */
    @Override
    public OrderInfo transfer(PayUser sourceUser, PayUser targetAccount, Money money) throws Exception {

        // 校验支付密码
        if (sourceUser.isVerifyByPwd()) {
            memberService.checkPayPwd(sourceUser);
        }

        // 获取余额
        sourceUser.setAvailable(memberService.balance(sourceUser.getId()));
        targetAccount.setAvailable(memberService.balance(targetAccount.getId()));

        // 创建订单信息
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(new OrderId(commonService.getId()));
        orderInfo.setPayer(sourceUser);
        orderInfo.setPayee(targetAccount);
        orderInfo.setTotalAmount(money);
        orderInfo.setPayAmount(money);
        orderInfo.setFeeAmount(new Money(BigDecimal.ZERO, null));
        orderInfo.setOrg(sourceUser.getOrg());
        orderInfo.setOrderType(OrderTypeEnum.TRANSFER);
        orderInfo.setOrderStatus(OrderStatusEnum.INIT);

        // 落库
        orderInfoRepository.save(orderInfo);

        // 转账金额预扣处理
        sourceUser.deposit(money);
        targetAccount.deposit(money);

        // 发起渠道转账
        OrderStatusEnum statusEnum = channelService.agentPay(sourceUser, targetAccount, money, orderInfo.getOrderType());
        orderInfo.setOrderStatus(statusEnum);
        return orderInfo;
    }
}
