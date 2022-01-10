package com.ts.trans.domain.external;

import com.ts.trans.domain.entity.PayUser;
import com.ts.trans.types.Money;
import com.ts.trans.types.OrderStatusEnum;
import com.ts.trans.types.OrderTypeEnum;

/**
 * 渠道服务接口
 */
public interface ChannelService {

    OrderStatusEnum agentPay(PayUser payer, PayUser payee, Money money, OrderTypeEnum orderType);
}
