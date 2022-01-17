package com.ts.trans.application.impl;

import com.ts.bfs.common.exception.BFException;
import com.ts.trans.api.dto.OrderInfoDTO;
import com.ts.trans.api.facade.TransOrderQueryFacade;
import com.ts.trans.domain.entity.OrderInfo;
import com.ts.trans.domain.repository.OrderInfoRepository;
import com.ts.trans.types.OrderId;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Slf4j
public class TransOrderQueryFacadeImpl implements TransOrderQueryFacade {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Override
    public OrderInfoDTO getInfo(String orderNo) {
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        OrderId orderId;
        OrderInfo orderInfo;
        try {
            // 根据订单编号查询订单信息
            orderId = new OrderId(orderNo);
            orderInfo = orderInfoRepository.findByOrderNo(orderId);
            // 响应结果转换: entity -> DTO
            orderInfoDTO.setOrderNo(orderInfo.getOrderId().getStrValue());
            orderInfoDTO.setChannelCode(orderInfo.getChannel().getCode());
            orderInfoDTO.setOrderStatus(orderInfo.getOrderStatus().getCode());
            orderInfoDTO.setOrderType(orderInfo.getOrderType().getType());
            orderInfoDTO.setCurrency(orderInfo.getTotalAmount().getCurrency().getValue());
            orderInfoDTO.setTotalAmount(orderInfo.getTotalAmount().getAmout());
            orderInfoDTO.setPayAmount(orderInfo.getPayAmount().getAmout());
            orderInfoDTO.setFeeAmount(orderInfo.getFeeAmount().getAmout());
            orderInfoDTO.setOrgCode(orderInfo.getOrg().getCode());
            orderInfoDTO.setSuccessDate(orderInfo.getSuccessDate());
            orderInfoDTO.setCreateTime(orderInfo.getCreateTime());
            orderInfoDTO.setPayerId(orderInfo.getPayer().getId().getStrValue());
            orderInfoDTO.setPayerName(orderInfo.getPayer().getName());
            orderInfoDTO.setPayeeId(orderInfo.getPayee().getId().getStrValue());
            orderInfoDTO.setPayeeId(orderInfo.getPayee().getName());
        } catch (Exception e) {
            log.error("订单查询异常", e);
            throw new BFException("订单查询异常，原因:" + e.getMessage());
        }
        return orderInfoDTO;
    }
}
