package com.ts.trans.repository;

import com.ts.trans.domain.entity.OrderInfo;
import com.ts.trans.domain.entity.PayUser;
import com.ts.trans.repository.mapper.OrderInfoDO;
import com.ts.trans.types.*;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * 对象转换
 * 处理 value object 与 data object 之间的转换
 */
@Component
public class OrderInfoBuilder {

    public OrderInfo toOrderInfo(OrderInfoDO orderInfoDO) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(new OrderId(orderInfoDO.getOrderNo()));
        orderInfo.setOrderType(OrderTypeEnum.getByType(orderInfoDO.getOrderType()));
        orderInfo.setOrg(new Org(orderInfoDO.getOrgCode()));
        orderInfo.setOrderStatus(OrderStatusEnum.valueOfCode(orderInfoDO.getOrderStatus()));
        orderInfo.setCreateTime(orderInfoDO.getCreateTime());
        orderInfo.setSuccessDate(orderInfoDO.getSuccessDate());
        PayUser payerUser = new PayUser();
        payerUser.setId(new MemberId(orderInfoDO.getPayerId()));
        payerUser.setOrg(new Org(orderInfoDO.getOrgCode()));
        payerUser.setName(orderInfoDO.getPayerName());
        payerUser.setChannel(new Channel(orderInfoDO.getChannelCode()));
        orderInfo.setPayer(payerUser);
        orderInfo.setChannel(payerUser.getChannel());
        PayUser payeeUser = new PayUser();
        payeeUser.setId(new MemberId(orderInfoDO.getPayeeId()));
        payeeUser.setOrg(new Org(orderInfoDO.getOrgCode()));
        payeeUser.setName(orderInfoDO.getPayeeName());
        payeeUser.setChannel(new Channel(orderInfoDO.getChannelCode()));
        orderInfo.setPayee(payeeUser);
        orderInfo.setChannel(new Channel(orderInfoDO.getChannelCode()));
        orderInfo.setPayAmount(new Money(orderInfoDO.getPayAmount(), new Currency("CNY")));
        orderInfo.setTotalAmount(new Money(orderInfoDO.getTotalAmount(), new Currency("CNY")));
        orderInfo.setFeeAmount(new Money(orderInfoDO.getFeeAmount(), new Currency("CNY")));
        return orderInfo;
    }

    public OrderInfoDO fromOrderInfo(OrderInfo orderInfo) throws Exception {
        OrderInfoDO orderInfoDO = new OrderInfoDO();
        orderInfoDO.setId(orderInfo.getOrderId().getValue());
        orderInfoDO.setOrderType(orderInfo.getOrderType().getType());
        orderInfoDO.setPayAmount(orderInfo.getPayAmount().getAmout());
        orderInfoDO.setFeeAmount(orderInfo.getFeeAmount().getAmout());
        orderInfoDO.setTotalAmount(orderInfo.getTotalAmount().getAmout());
        orderInfoDO.setPayerId(orderInfo.getPayer().getId().getStrValue());
        orderInfoDO.setPayerName(orderInfo.getPayer().getName());
        orderInfoDO.setPayeeId(orderInfo.getPayee().getId().getStrValue());
        orderInfoDO.setPayeeName(orderInfo.getPayee().getName());
        orderInfoDO.setChannelCode(orderInfo.getChannel().getCode());
        orderInfoDO.setOrgCode(orderInfo.getOrg().getCode());
        orderInfoDO.setOrderStatus(orderInfo.getOrderStatus().getCode());
        orderInfoDO.setCreateTime(new Date());
        orderInfoDO.setSuccessDate(orderInfo.getSuccessDate());
        return orderInfoDO;
    }
}
