package com.ts.trans.domain.service;

import com.ts.trans.domain.entity.OrderInfo;
import com.ts.trans.domain.entity.PayUser;
import com.ts.trans.types.Money;

/**
 * 转账服务接口
 */
public interface TransferService {

    /**
     * 转账交易
     * @param sourceUser
     * @param targetAccount
     * @param money
     * @return
     * @throws Exception
     */
    OrderInfo transfer(PayUser sourceUser, PayUser targetAccount, Money money) throws Exception;
}
