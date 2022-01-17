package com.ts.trans.domain.repository;

import com.ts.trans.domain.entity.OrderInfo;
import com.ts.trans.types.OrderId;

/**
 * 订单存储
 */
public interface OrderInfoRepository {

    /**
     * 订单查询
     * @param id
     * @return
     * @throws Exception
     */
    OrderInfo findByOrderNo(OrderId id) throws Exception;

    /**
     * 根据主键ID查询订单
     * @param id
     * @return
     * @throws Exception
     */
    OrderInfo find(Long id) throws Exception;

    /**
     * 保存
     * @param orderInfo
     * @return
     * @throws Exception
     */
    boolean save(OrderInfo orderInfo) throws Exception;
}
