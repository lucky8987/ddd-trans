package com.ts.trans.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ts.trans.domain.entity.OrderInfo;
import com.ts.trans.domain.repository.OrderInfoRepository;
import com.ts.trans.repository.mapper.OrderInfoMapper;
import com.ts.trans.repository.mapper.OrderInfoDO;
import com.ts.trans.types.OrderId;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单持久化
 */
@Component
public class OrderInfoRepositoryImpl implements OrderInfoRepository {

    @Autowired
    private OrderInfoMapper orderInfoDAO;

    @Autowired
    private OrderInfoBuilder orderInfoBuilder;

    @Override
    public OrderInfo findByOrderNo(OrderId id) throws Exception {
        OrderInfoDO orderInfoDO = orderInfoDAO
                .selectOne(new QueryWrapper<OrderInfoDO>().lambda().eq(OrderInfoDO::getOrderNo, id.getStrValue()));
        return orderInfoBuilder.toOrderInfo(orderInfoDO);
    }

    @Override
    public OrderInfo find(Long id) throws Exception {
        OrderInfoDO orderInfoDO = orderInfoDAO.selectById(id);
        return orderInfoBuilder.toOrderInfo(orderInfoDO);
    }

    @Override
    public boolean save(OrderInfo orderInfo) throws Exception {
        boolean result;
        OrderInfoDO orderInfoDO = orderInfoBuilder.fromOrderInfo(orderInfo);
        if (orderInfoDO != null) {
            // 修改
            result = BooleanUtils.toBoolean(orderInfoDAO.insert(orderInfoDO));
        } else {
            // 新增
            result = BooleanUtils.toBoolean(orderInfoDAO.updateById(orderInfoDO));
        }
        return result;
    }
}
