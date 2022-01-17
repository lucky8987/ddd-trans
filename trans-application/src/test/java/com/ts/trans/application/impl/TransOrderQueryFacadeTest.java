package com.ts.trans.application.impl;

import com.alibaba.fastjson.JSON;
import com.ts.trans.api.dto.OrderInfoDTO;
import com.ts.trans.api.facade.TransOrderQueryFacade;
import com.ts.trans.types.OrderId;
import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TransOrderQueryFacadeTest {

    @Autowired
    TransOrderQueryFacade queryFacade;

    @Test
    public void getInfo() {
        OrderInfoDTO orderDto = queryFacade.getInfo("285202687115423744");
        log.info("订单详情：{}", JSON.toJSONString(orderDto));
    }
}