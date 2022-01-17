package com.ts.trans.api.facade;

import com.ts.trans.api.dto.OrderInfoDTO;

/**
 * 订单查询
 */
public interface TransOrderQueryFacade {

    /**
     * 订单详情
     * @param orderNo
     * @return
     */
    OrderInfoDTO getInfo(String orderNo);
}
